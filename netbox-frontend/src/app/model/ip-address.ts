import { Device } from "./device";

export class IpAddress {
  id!: number;
  address!: string;
  status: string = 'Active'; 
  dnsName!: string;
  
  device: Device = new Device();
}