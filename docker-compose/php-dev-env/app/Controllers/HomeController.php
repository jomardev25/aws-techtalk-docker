<?php

declare(strict_types=1);

namespace App\Controllers;

use App\View;
use App\Attributes\Get;
use App\Attributes\Post;
use App\Attributes\Route;
use App\Enums\HttpMethod;
use Twig\Environment as Twig;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\RedirectResponse;
use App\Contracts\Services\EmployeeServiceInterface as ServicesEmployeeServiceInterface;

class HomeController
{
    public function __construct(
        private Twig $twig,
        private Request $request,
        private ServicesEmployeeServiceInterface $employeeService
    )
    {
    }

    #[Get('/')]
    #[Route('/home', HttpMethod::Head)]
    public function index(): string
    {
        $employees = $this->employeeService->getAllEmployees();
        return $this->twig->render('home/index.twig', ['employees' => $employees]);
    }

    #[Post('/')]
    public function store(): RedirectResponse
    {

        $this->employeeService->createEmployee($this->request);
        return new RedirectResponse('/');
    }

    #[Get('/about')]
    #[Route('/about', HttpMethod::Head)]
    public function about(): string
    {
        return $this->twig->render('home/about.twig');
    }

    #[Get('/contact-us')]
    #[Route('/contact-us', HttpMethod::Head)]
    public function contactUs(): string
    {
        return $this->twig->render('home/contactus.twig');
    }
}
