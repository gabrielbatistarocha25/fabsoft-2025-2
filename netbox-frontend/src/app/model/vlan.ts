import { Site } from "./site";

export class Vlan {
  id!: number;
  name!: string;
  vlanId!: number;
  site: Site = new Site();
}