module com.example {
  'use strict';
  import IResourceClass = ng.resource.IResourceClass;

  export class ListService<T> {
    constructor(private resource: IResourceClass<T>) {
    }

    getAll(queryParameters?: any): Array<T> {
      return <Array<T>> this.resource.query(queryParameters);
    }
  }

  export class ListServiceProvider<T> {
    private endPoint: string = null;

    public setResource(endPoint: string): ListServiceProvider<T> {
      this.endPoint = endPoint;
      return this;
    }

    /* @ngInject */
    public $get($resource: ng.resource.IResourceService): ListService<T> {
      if (this.endPoint) {
        const resource = $resource<T>(this.endPoint);
        return new ListService<T>(<IResourceClass<T>> resource);
      }
      throw Error('ListService configured without end point.');
    }
  }
}
