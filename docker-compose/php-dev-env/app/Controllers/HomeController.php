<?php

declare(strict_types = 1);

namespace App\Controllers;

use App\Attributes\Get;
use App\Attributes\Post;
use App\Attributes\Route;
use App\Enums\HttpMethod;
use App\Models\Employee;
use App\View;

class HomeController
{
    #[Get('/')]
    #[Route('/home', HttpMethod::Head)]
    public function index(): View
    {
        $employees = Employee::all();
        return View::make('home/index', ['employees' => $employees]);
    }

    #[Post('/')]
    public function store(): void
    {
        Employee::create([
            'first_name' => $_POST['first_name'],
            'last_name' => $_POST['last_name'],
            'email' => $_POST['email'],
            'phone_num' => $_POST['phone_num']
        ]);

        header('Location: /');
    }

    #[Get('/about')]
    #[Route('/about', HttpMethod::Head)]
    public function test(): View
    {
        return View::make('home/about');
    }

    #[Get('/contact-us')]
    #[Route('/contact-us', HttpMethod::Head)]
    public function hello(): View
    {
        return View::make('home/contactus');
    }
    
}