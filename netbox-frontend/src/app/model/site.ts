import { Location } from "./location";

export class Site {
  id!: number;
  name!: string;
  location: Location = new Location();
}