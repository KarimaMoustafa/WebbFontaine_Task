import {Commit} from "./commit";
import {Stats} from "./stats";

export interface CommitResult {
  commit: Commit;
  stats: Stats;
  sha: string;
}
