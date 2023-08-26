import React, { useState, useEffect } from "react";
import { BASE_URL } from "../../Config";
import "./user.css";

const User = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                let token = localStorage.getItem("token");
                const response = await fetch(`${BASE_URL}/users`, {
                    method: 'GET',
                    headers: { 'Authorization': 'Bearer ' + token, 'Content-Type': 'application/json'}
                });
                const jsonData = await response.json();
                setData(jsonData);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();

    }, []);

    return (
        <div>
            <table cellPadding={0} cellSpacing={0}>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        data?.map(data =>
                            <tr key={data.id}>
                                <td>{data.username}</td>
                                <td>{data.email}</td>
                                <td>{data.first_name}</td>
                                <td>{data.last_name}</td>
                            </tr>
                        )}
                </tbody>
            </table>
        </div>
    );
};

export default User;