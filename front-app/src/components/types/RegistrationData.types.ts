import { ClientData } from "./ClientData.types";

export interface RegistrationData {
    client : ClientData;
    password : string;
  
  }

  export interface Props {
      registrationData: RegistrationData;
  }