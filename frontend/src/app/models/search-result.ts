import {Repository} from "./repository";

export interface SearchResult {
  total_count: number;
  items: Repository[];
}
