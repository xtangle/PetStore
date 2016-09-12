describe('ResourceServiceProvider', function() {
    var fixture;
    var endPoint = '/api/foo';
    var actions = {
        update: {
            method: 'PUT',
            url: '/api/bar'
        }
    };

    var response_get = {id: 1, name: 'foo get'};
    var response_post = {id: 2, name: 'foo post'};
    var response_delete = {id: 3, name: 'foo delete'};
    var response_put = {id: 4, name: 'foo put'};

    var httpBackend;

    beforeEach(module('com.example.common'));

    function configureTestProvider(endPoint, actions) {
        return module(function configureService(PetServiceProvider) {
            PetServiceProvider.setResource(endPoint);
            PetServiceProvider.withActions(actions);
        });
    }

    beforeEach(configureTestProvider(endPoint, actions));

    beforeEach(inject(function setUp(_PetService_) {
        fixture = _PetService_;
    }));

    beforeEach(inject(function mockBackend(_$httpBackend_) {
        httpBackend = _$httpBackend_;
        _$httpBackend_.whenGET(endPoint).respond(response_get);
        _$httpBackend_.whenPOST(endPoint).respond(response_post);
        _$httpBackend_.whenDELETE(endPoint).respond(response_delete);
        _$httpBackend_.whenPUT(actions.update.url).respond(response_put);
    }));

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation ();
        httpBackend.verifyNoOutstandingRequest ();
    });

    it('should return response when get is called', function () {
        var actual = fixture.getResource().get();
        var expected = response_get;
        httpBackend.flush();
        expect(intersectionWithObject(actual, expected)).toEqual(expected);
    });

    it('should return response when save is called', function () {
        var actual = fixture.getResource().save();
        var expected = response_post;
        httpBackend.flush();
        expect(intersectionWithObject(actual, expected)).toEqual(expected);
    });

    it('should return response when delete is called', function () {
        var actual = fixture.getResource().delete();
        var expected = response_delete;
        httpBackend.flush();
        expect(intersectionWithObject(actual, expected)).toEqual(expected);
    });

    it('should return response when update is called', function () {
        var actual = fixture.getResource().update();
        var expected = response_put;
        httpBackend.flush();
        expect(intersectionWithObject(actual, expected)).toEqual(expected);
    });

    function intersectionWithObject(object1, object2) {
        return _.pick(object1, _.intersection(_.keys(object1), _.keys(object2)));
    }
});