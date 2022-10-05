package com.repasofinal.uadeflix.support;

import com.repasofinal.uadeflix.logic.Catalog;
import com.repasofinal.uadeflix.logic.Movie;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    public static List<Catalog> GetCatalogs() {
        List<Catalog> catalogs = new ArrayList<Catalog>();

        List<Movie> catalog_1 = new ArrayList<Movie>();
        List<Movie> catalog_2 = new ArrayList<Movie>();
        List<Movie> catalog_3 = new ArrayList<Movie>();

        catalog_1.add(new Movie("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg","Shawshank Redemption","","1994","","","Morgan Freeman, Tim Robins","","Drama","PG-13",""));
        catalog_1.add(new Movie("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg","Batman: The Dark Knight","","1994","","","Morgan Freeman, Tim Robins","","Drama","PG-13",""));
        catalog_1.add(new Movie("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg","Shawshank Redemption","","1994","","","Morgan Freeman, Tim Robins","","Drama","PG-13",""));

        catalog_2.add(new Movie("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg","Shawshank Redemption","","1994","","","Morgan Freeman, Tim Robins","","Drama","PG-13",""));
        catalog_3.add(new Movie("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg","Shawshank Redemption","","1994","","","Morgan Freeman, Tim Robins","","Drama","PG-13",""));

        catalogs.add(new Catalog("Catalog 1", catalog_1));
        catalogs.add(new Catalog("Catalog 2", catalog_2));
        catalogs.add(new Catalog("Catalog 3", catalog_3));

        return catalogs;
    }


}
