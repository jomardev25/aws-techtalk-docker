package com.github.jomardev25.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.jomardev25.exception.FileNotFoundException;
import com.github.jomardev25.exception.FileStorageException;
import com.github.jomardev25.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {

  @Value("${application.file.upload-dir}")
  private String uploadDir;
  private Path root;

  @Override
  public void init() {
    
    try {
      root =  Paths.get(uploadDir);
			Files.createDirectories(root);
		}
		catch (IOException ex) {
			throw new FileStorageException("Could not initialize file storage.", ex);
		}
  }

  @Override
  public String store(MultipartFile file) {
    try {
      if(file.isEmpty()) {
        throw new FileStorageException("Failed to store empty file.");
      }

      String fileName = StringUtils.cleanPath(file.getOriginalFilename());

      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry! File name which contains invalid path sequence " + fileName);
      }

      Path destinationFile = this.root.resolve(fileName).normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.root.toAbsolutePath())) {
				throw new FileStorageException("Cannot store file outside current directory.");
			}

      try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
			}

      return fileName;

    } catch (IOException ex) {
      throw new FileStorageException("Failed to store file.", ex);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
			return Files.walk(this.root, 1)
				.filter(path -> !path.equals(this.root))
				.map(this.root::relativize);
		}
		catch (IOException ex) {
			throw new FileStorageException("Failed to read stored files.", ex);
		}
  }

  @Override
  public Path load(String filename) {
    return root.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new FileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new FileNotFoundException("Could not read file: " + filename);
		}
  }

  @Override
  public boolean delete(String filename) {
    File file = new File(root.resolve(filename).toAbsolutePath().toString());
    if(!file.exists()) {
      throw new FileNotFoundException("Could not find file: " + filename);
    }

    return file.delete();
  }

  @Override
  public void deleteAll(String dirPath) {
    FileSystemUtils.deleteRecursively(root.toFile());
  }
}
