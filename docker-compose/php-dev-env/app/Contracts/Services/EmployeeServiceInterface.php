<?php

declare(strict_types = 1);

namespace App\Contracts\Services;

use Illuminate\Database\Eloquent\Collection;

interface EmployeeServiceInterface
{
    function getAllEmployees(): Collection;
    function createEmployee($request): void;
}