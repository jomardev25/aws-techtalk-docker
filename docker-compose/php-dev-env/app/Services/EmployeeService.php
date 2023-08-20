<?php

declare(strict_types = 1);

namespace App\Services;
use App\Models\Employee;
use App\Contracts\Services\EmployeeServiceInterface;
use Illuminate\Database\Eloquent\Collection;

class EmployeeService implements EmployeeServiceInterface
{
    public function getAllEmployees(): Collection
    {
        return Employee::all();
    }

    public function createEmployee($request): void
    {
        Employee::create([
            'first_name' => $request->get('first_name'),
            'last_name' => $request->get('last_name'),
            'email' => $request->get('email'),
            'phone_num' => $request->get('phone_num')
        ]);
    }
}