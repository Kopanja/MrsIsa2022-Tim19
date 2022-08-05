import { Outlet, Navigate } from "react-router-dom";
import TokenService from '../services/TokenService';

const AdminRoutes = () => {
    let auth = TokenService.getRole() === "ADMIN";

    return(
        auth ? <Outlet/> : <Navigate to = "/unauthorized"/>
    )
}


export default AdminRoutes;