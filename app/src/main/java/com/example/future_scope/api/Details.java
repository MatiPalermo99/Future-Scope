package com.example.future_scope.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Details implements Parcelable {

    @Override
    public String toString() {
        return "Details{" +
                "adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", belongsToCollection=" + belongsToCollection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", productionCountries=" + productionCountries +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spokenLanguages=" + spokenLanguages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }

    /**
     * adult : false
     * backdrop_path : /srYya1ZlI97Au4jUYAktDe3avyA.jpg
     * belongs_to_collection : {"id":468552,"name":"Wonder Woman Collection","poster_path":"/8AQRfTuTHeFTddZN4IUAqprN8Od.jpg","backdrop_path":"/n9KlvCOBFDmSyw3BgNrkUkxMFva.jpg"}
     * budget : 200000000
     * genres : [{"id":14,"name":"Fantasy"},{"id":28,"name":"Action"},{"id":12,"name":"Adventure"}]
     * homepage : https://www.warnerbros.com/movies/wonder-woman-1984
     * id : 464052
     * imdb_id : tt7126948
     * original_language : en
     * original_title : Wonder Woman 1984
     * overview : Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.
     * popularity : 2247.44
     * poster_path : /8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg
     * production_companies : [{"id":9993,"logo_path":"/2Tc1P3Ac8M479naPp1kYT3izLS5.png","name":"DC Entertainment","origin_country":"US"},{"id":174,"logo_path":"/ky0xOc5OrhzkZ1N6KyUxacfQsCk.png","name":"Warner Bros. Pictures","origin_country":"US"},{"id":114152,"logo_path":null,"name":"The Stone Quarry","origin_country":"US"},{"id":128064,"logo_path":"/13F3Jf7EFAcREU0xzZqJnVnyGXu.png","name":"DC Films","origin_country":"US"},{"id":507,"logo_path":"/z7H707qUWigbjHnJDMfj6QITEpb.png","name":"Atlas Entertainment","origin_country":"US"},{"id":429,"logo_path":"/2Tc1P3Ac8M479naPp1kYT3izLS5.png","name":"DC Comics","origin_country":"US"}]
     * production_countries : [{"iso_3166_1":"US","name":"United States of America"}]
     * release_date : 2020-12-16
     * revenue : 154600000
     * runtime : 152
     * spoken_languages : [{"english_name":"English","iso_639_1":"en","name":"English"}]
     * status : Released
     * tagline : A new era of wonder begins.
     * title : Wonder Woman 1984
     * video : false
     * vote_average : 7.0
     * vote_count : 3625
     */



    private Boolean adult;
    private String backdropPath;
    private BelongsToCollectionDTO belongsToCollection;
    private Integer budget;
    private List<GenresDTO> genres;
    private String homepage;
    private Integer id;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    private List<ProductionCompaniesDTO> productionCompanies;
    private List<ProductionCountriesDTO> productionCountries;
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    private List<SpokenLanguagesDTO> spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double voteAverage;
    private Integer voteCount;

    protected Details(Parcel in) {
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        backdropPath = in.readString();
        if (in.readByte() == 0) {
            budget = null;
        } else {
            budget = in.readInt();
        }
        homepage = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        imdbId = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        posterPath = in.readString();
        releaseDate = in.readString();
        if (in.readByte() == 0) {
            revenue = null;
        } else {
            revenue = in.readInt();
        }
        if (in.readByte() == 0) {
            runtime = null;
        } else {
            runtime = in.readInt();
        }
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readInt();
        }
    }

    public static final Creator<Details> CREATOR = new Creator<Details>() {
        @Override
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        @Override
        public Details[] newArray(int size) {
            return new Details[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollectionDTO getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollectionDTO belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<GenresDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresDTO> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompaniesDTO> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompaniesDTO> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountriesDTO> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountriesDTO> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguagesDTO> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguagesDTO> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        dest.writeString(backdropPath);
        if (budget == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(budget);
        }
        dest.writeString(homepage);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(imdbId);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        if (revenue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(revenue);
        }
        if (runtime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(runtime);
        }
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        if (voteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(voteCount);
        }
    }

    public static class BelongsToCollectionDTO implements Parcelable{
        /**
         * id : 468552
         * name : Wonder Woman Collection
         * poster_path : /8AQRfTuTHeFTddZN4IUAqprN8Od.jpg
         * backdrop_path : /n9KlvCOBFDmSyw3BgNrkUkxMFva.jpg
         */

        private Integer id;
        private String name;
        private String posterPath;
        private String backdropPath;

        protected BelongsToCollectionDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            name = in.readString();
            posterPath = in.readString();
            backdropPath = in.readString();
        }

        public static final Creator<BelongsToCollectionDTO> CREATOR = new Creator<BelongsToCollectionDTO>() {
            @Override
            public BelongsToCollectionDTO createFromParcel(Parcel in) {
                return new BelongsToCollectionDTO(in);
            }

            @Override
            public BelongsToCollectionDTO[] newArray(int size) {
                return new BelongsToCollectionDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(name);
            dest.writeString(posterPath);
            dest.writeString(backdropPath);
        }
    }

    public static class GenresDTO implements Parcelable{
        /**
         * id : 14
         * name : Fantasy
         */

        private Integer id;
        private String name;

        @Override
        public String toString() {
            return "GenresDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        protected GenresDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            name = in.readString();
        }

        public static final Creator<GenresDTO> CREATOR = new Creator<GenresDTO>() {
            @Override
            public GenresDTO createFromParcel(Parcel in) {
                return new GenresDTO(in);
            }

            @Override
            public GenresDTO[] newArray(int size) {
                return new GenresDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(name);
        }
    }

    public static class ProductionCompaniesDTO implements Parcelable{
        /**
         * id : 9993
         * logo_path : /2Tc1P3Ac8M479naPp1kYT3izLS5.png
         * name : DC Entertainment
         * origin_country : US
         */

        private Integer id;
        private String logoPath;
        private String name;
        private String originCountry;

        protected ProductionCompaniesDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            logoPath = in.readString();
            name = in.readString();
            originCountry = in.readString();
        }

        public static final Creator<ProductionCompaniesDTO> CREATOR = new Creator<ProductionCompaniesDTO>() {
            @Override
            public ProductionCompaniesDTO createFromParcel(Parcel in) {
                return new ProductionCompaniesDTO(in);
            }

            @Override
            public ProductionCompaniesDTO[] newArray(int size) {
                return new ProductionCompaniesDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(logoPath);
            dest.writeString(name);
            dest.writeString(originCountry);
        }
    }

    public static class ProductionCountriesDTO implements Parcelable{
        /**
         * iso_3166_1 : US
         * name : United States of America
         */

        private String iso31661;
        private String name;


        protected ProductionCountriesDTO(Parcel in) {
            iso31661 = in.readString();
            name = in.readString();
        }

        public static final Creator<ProductionCountriesDTO> CREATOR = new Creator<ProductionCountriesDTO>() {
            @Override
            public ProductionCountriesDTO createFromParcel(Parcel in) {
                return new ProductionCountriesDTO(in);
            }

            @Override
            public ProductionCountriesDTO[] newArray(int size) {
                return new ProductionCountriesDTO[size];
            }
        };

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(iso31661);
            dest.writeString(name);
        }
    }

    public static class SpokenLanguagesDTO implements Parcelable{
        /**
         * english_name : English
         * iso_639_1 : en
         * name : English
         */

        private String englishName;
        private String iso6391;
        private String name;

        protected SpokenLanguagesDTO(Parcel in) {
            englishName = in.readString();
            iso6391 = in.readString();
            name = in.readString();
        }

        public static final Creator<SpokenLanguagesDTO> CREATOR = new Creator<SpokenLanguagesDTO>() {
            @Override
            public SpokenLanguagesDTO createFromParcel(Parcel in) {
                return new SpokenLanguagesDTO(in);
            }

            @Override
            public SpokenLanguagesDTO[] newArray(int size) {
                return new SpokenLanguagesDTO[size];
            }
        };

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(englishName);
            dest.writeString(iso6391);
            dest.writeString(name);
        }
    }
}
