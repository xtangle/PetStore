module com.example {
  'use strict';
  import IResourceClass = angular.resource.IResourceClass;

  export class ResourceService<T> {
    constructor(private resource: IResourceClass<T>) {
    }

    getResource(): IResourceClass<T> {
      return this.resource;
    }
  }

  export class ResourceServiceProvider<T> {
    private endPoint: string = null;
    private actions: any = null;

    public setResource(endPoint: string): ResourceServiceProvider<T> {
      this.endPoint = endPoint;
      return this;
    }

    public withActions(actions: any): ResourceServiceProvider<T> {
      this.actions = actions;
      return this;
    }

    /* @ngInject */
    public $get($resource: ng.resource.IResourceService): ResourceService<T> {
      if (this.endPoint) {
        return new ResourceService<T>($resource(this.endPoint, {}, this.actions));
      }
      throw Error('ResourceService not configured');
    }

  }
}
