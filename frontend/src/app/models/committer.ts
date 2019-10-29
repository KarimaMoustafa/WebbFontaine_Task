import {Commit} from "./commit";
import {Stats} from "./stats";

export interface Committer {
  name: string;
  email: string;
  date: Date;
}
