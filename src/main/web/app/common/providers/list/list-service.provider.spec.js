describe('ListItemServiceProvider', function() {
    var fixture;
    var response = ["foo", "bar"];
    var endPoint = '/api/foo';
    var httpBackend;

    beforeEach(module('com.example.common'));

    function configureTestProvider(endPoint) {
        return module(function configureService(PetCategoryServiceProvider) {
            PetCategoryServiceProvider.setResource(endPoint);
        });
    }

    beforeEach(configureTestProvider(endPoint));

    beforeEach(inject(function setUp(_PetCategoryService_) {
        fixture = _PetCategoryService_;
    }));

    beforeEach(inject(function mockBackend(_$httpBackend_) {
        httpBackend = _$httpBackend_;
        _$httpBackend_.expectGET(endPoint).respond(response);
    }));

    afterEach(inject(function assertBackendCalls(_$httpBackend_) {
        _$httpBackend_.verifyNoOutstandingExpectation();
        _$httpBackend_.verifyNoOutstandingRequest();
    }));

    it('should return all resource items', function () {
        var actual = fixture.getAll();
        var expected = response;

        httpBackend.flush();
        expect(_.intersectionWith(expected, actual, _.isEqual)).toEqual(expected);
    });

});
