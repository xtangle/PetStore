module com.example {
  'use strict';
  import IResource = angular.resource.IResource;
  import IResourceClass = angular.resource.IResourceClass;

  const _ = require('lodash');
  const PET_TAG_DELIMITER: string = ';';
  const PET_PHOTO_URL_DELIMITER: string = '\n';

  export class AddPetController {
    successMsg: string;
    errorMsg: string;
    petNameValidationErrorMsg: string;
    petCategoryValidationErrorMsg: string;
    petStatusValidationErrorMsg: string;

    petName: string;
    petCategory: string;
    petStatus: string;
    petTags: string;
    petPhotoURLs: string;

    /* @ngInject */
    constructor(public categories: Array<string>, public statuses: Array<string>, private petResource: IResourceClass<IResource<IPet>>) {
    }

    public addPet(): void {
      this.clearMessages();
      if (!this.validate()) {
        return;
      }

      let petTagsArray: Array<string> =
        _.filter(_.map(_.split(this.petTags, PET_TAG_DELIMITER), _.trim), s => s !== '');
      let petPhotoURLsArray: Array<string> =
        _.filter(_.map(_.split(this.petPhotoURLs, PET_PHOTO_URL_DELIMITER), _.trim), s => s !== '');

      let pet: IPet = {
        category: this.petCategory,
        id: null,
        name: this.petName,
        photoURLs: petPhotoURLsArray,
        status: this.petStatus,
        tags: petTagsArray,
      };

      this.petResource.save(pet,
        response => this.successMsg = 'Successfully added pet \'' + response.name + '\' (id: ' + response.id + ')',
        () => this.errorMsg = 'Error: could not add pet!');
      this.clearInputFields();
    }

    private clearInputFields(): void {
      this.petName = null;
      this.petCategory = null;
      this.petStatus = null;
      this.petTags = null;
      this.petPhotoURLs = null;
    }

    private clearMessages(): void {
      this.successMsg = null;
      this.errorMsg = null;
      this.petNameValidationErrorMsg = null;
      this.petCategoryValidationErrorMsg = null;
      this.petStatusValidationErrorMsg = null;
    }

    private validate(): boolean {
      if (!this.petName) {
        this.petNameValidationErrorMsg = 'Enter the pet\'s name';
        return false;
      }
      if (!this.petCategory) {
        this.petCategoryValidationErrorMsg = 'Select a category';
        return false;
      }
      if (!this.petStatus) {
        this.petStatusValidationErrorMsg = 'Select a status';
        return false;
      }
      return true;
    }
  }
}
