module com.example {
  'use strict';

  export interface IPet {
    id: string;
    name: string;
    category: string;
    photoURLs: Array<string>;
    tags: Array<string>;
    status: string;
  }
}
