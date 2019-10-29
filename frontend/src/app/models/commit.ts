import {Committer} from "./committer";

export interface Commit {
  committer: Committer;
  message: string;
}
