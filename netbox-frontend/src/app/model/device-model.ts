import { Manufacturer } from "./manufacturer";

export class DeviceModel {
  id!: number;
  name!: string;
  manufacturer: Manufacturer = new Manufacturer();
}