package com.example.momocinema.data

import com.example.momocinema.R
import com.example.momocinema.model.Cast
import com.example.momocinema.model.Film
import com.example.momocinema.model.Ranking

class Datasource() {

    fun loadCast(): List<Cast> {
        return listOf<Cast>(
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/%ED%8B%B0%EB%AA%A8%EC%8B%9C_%EC%83%AC%EB%9D%BC%EB%A9%94_%28Timothee_Chalamet%29_%27%EB%8D%94_%ED%82%B9_%ED%97%A8%EB%A6%AC_5%EC%84%B8%27_04.png/800px-%ED%8B%B0%EB%AA%A8%EC%8B%9C_%EC%83%AC%EB%9D%BC%EB%A9%94_%28Timothee_Chalamet%29_%27%EB%8D%94_%ED%82%B9_%ED%97%A8%EB%A6%AC_5%EC%84%B8%27_04.png", name = "Timothée Chalamet", characterName = "Paul Atreides"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Zendaya_-_2019_by_Glenn_Francis.jpg/800px-Zendaya_-_2019_by_Glenn_Francis.jpg", name = "Zendaya", characterName = "Chani"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b3/Rebecca_Ferguson_in_2018.jpg", name = "Rebecca Ferguson", characterName = "Lady Jessica Atreides"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Cillian_Murphy_at_Berlinale_2024%2C_Ausschnitt.jpg/800px-Cillian_Murphy_at_Berlinale_2024%2C_Ausschnitt.jpg", name = "Cillian Murphy", characterName = "J. Robert Oppenheimer"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/800px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg", name = "Robert Downey Jr.", characterName = "Lewis Strauss")
        )
    }
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(title = "Dune: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh", picture = R.drawable.mai_poster, releaseDate = "08/03/2024", restrictAge = 16, tag = "Khoa học viễn tưởng, Phiêu lưu, Chính kịch, Hành động, Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1507),  duration = 104, language = "Phụ đề Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast()),
            Film(title = "Đào, phở và piano", picture = R.drawable.mai_poster, releaseDate = "14/06/2025", restrictAge = 18, tag = "Chính kịch", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 166, language = "Phụ đề", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast()),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "20/10/2024", restrictAge = 10, tag = "Chính kịch, Hành động, Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 305, language = "Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast()),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "11/12/2024", restrictAge = 13, tag = "Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 89, language = "Phụ đề Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast()),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "01/01/2024", restrictAge = 18, tag = "Chính kịch, Hành động", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 123, language = "Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast()),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "30/05/2024", restrictAge = 1, tag = "Chính kịch, Hành động, Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 195, language = "Phụ đề", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", cast = loadCast())

        )
    }




}