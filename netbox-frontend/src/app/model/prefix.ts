import { Site } from "./site";
import { Vlan } from "./vlan";

export class Prefix {
  id!: number;
  cidr!: string;
  name!: string;
  
  site: Site = new Site();
  vlan: Vlan = new Vlan();
}