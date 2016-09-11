module com.example {
  'use strict';
  import IResource = angular.resource.IResource;
  import IResourceClass = angular.resource.IResourceClass;

  export class FindPetController {
    successMsg: string;
    errorMsg: string;

    petIdQuery: string;
    selectedPet: IPet;

    /* @ngInject */
    constructor(private petResource: IResourceClass<IResource<IPet>>) {
    }

    public getPet(): void {
      this.clearMessages();
      if (!this.validate()) {
        return;
      }

      this.petResource.get({petIdQuery: this.petIdQuery},
        response => this.selectedPet = response.toJSON(),
        response => this.parseFailedResponse(response)
      );
    }

    public deletePet(): void {
      if (confirm('Are you sure you want to delete pet \'' + this.selectedPet.name + '\' (id: ' + this.selectedPet.id + ')?')) {
        this.clearMessages();
        this.petResource.remove({petIdQuery: this.petIdQuery},
          () => {
            this.successMsg = 'Successfully deleted pet \'' + this.selectedPet.name + '\' (id: ' + this.selectedPet.id + ')';
            this.resetQuery();
          },
          response => this.errorMsg = response.statusMessage
        );
      }
    }

    private resetQuery(): void {
      this.petIdQuery = null;
      this.selectedPet = null;
    }

    private clearMessages(): void {
      this.successMsg = null;
      this.errorMsg = null;
    }

    private parseFailedResponse(response: any): void {
      switch (response.status) {
        case 400:
          this.errorMsg = 'Not a valid id';
          break;
        case 404:
          this.errorMsg = 'No pet with id ' + this.petIdQuery + ' found';
          break;
        default:
          this.errorMsg = 'Unknown error';
      }
    }

    private validate(): boolean {
      if (!this.petIdQuery) {
        this.errorMsg = 'Please enter a pet id';
        return false;
      }
      return true;
    }
  }
}

