import { Site } from "./site";
import { Rack } from "./rack";
import { DeviceModel } from "./device-model";

export class Device {
  id!: number;
  name!: string;
  position!: number;
  
  site: Site = new Site();
  rack: Rack = new Rack();
  deviceModel: DeviceModel = new DeviceModel();
}