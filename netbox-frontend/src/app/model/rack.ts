import { Site } from "./site";

export class Rack {
  id!: number;
  name!: string;
  uHeight!: number;
  site: Site = new Site();
}