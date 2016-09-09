module com.example {
  'use strict';

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
    constructor(public categories: Array<string>, public statuses: Array<string>, private petResource) {
    }

    public addPet(): boolean {
      this.clearMessages();
      if (!this.validate()) {
        return false;
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

      let addPetCtrl = this;
      this.petResource.save(pet,
        function(savedPet: IPet) {
          addPetCtrl.successMsg = 'Successfully added pet \'' + savedPet.name + '\' (id: ' + savedPet.id + ')';
        },
        function() {
          addPetCtrl.errorMsg = 'Error: cannot add pet!';
        }
      );

      this.clearFields();
      return true;
    }

    private clearFields(): void {
      this.petName = '';
      this.petCategory = '';
      this.petStatus = '';
      this.petTags = '';
      this.petPhotoURLs = '';
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
