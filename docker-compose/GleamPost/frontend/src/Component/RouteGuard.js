import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';

const RouteGuard = () => {
    return localStorage.getItem("token") ? <Outlet /> : <Navigate to="/login" />;
 };
  
 export default RouteGuard;