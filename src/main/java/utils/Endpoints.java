package utils;

public enum Endpoints {
    //movie
    MOVIE_POPULAR("/movie/popular"),
    MOVIE_FROM_ID("/movie/${movie_id}"),


    //person
    PERSON_POPULAR("/person/popular");

    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}


