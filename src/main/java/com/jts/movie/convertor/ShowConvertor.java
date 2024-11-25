package com.jts.movie.convertor;

import com.jts.movie.model.entities.MovieShow;
import com.jts.movie.request.ShowRequest;

public class ShowConvertor {
    public static MovieShow showDTOtoShow(ShowRequest showRequest){
        MovieShow show= MovieShow.builder().time(showRequest.getShowTime()).date(showRequest.getShowDate()).build();
        return show;
    }

}
