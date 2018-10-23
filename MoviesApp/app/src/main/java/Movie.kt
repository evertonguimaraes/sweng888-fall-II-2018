class Movie {

    var title: String? = null
    var description: String? = null
    var rating: String? = null
    var year: Int = 0

    constructor() {

    }

    constructor(title: String, description: String, rating: String, year: Int) {
        this.title = title
        this.description = description
        this.rating = rating
        this.year = year
    }
}
