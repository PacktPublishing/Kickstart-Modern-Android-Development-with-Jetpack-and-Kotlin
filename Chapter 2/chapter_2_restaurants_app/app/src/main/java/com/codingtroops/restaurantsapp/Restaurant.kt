package com.codingtroops.restaurantsapp

data class Restaurant(val id: Int,
                      val title: String,
                      val description: String)

val dummyRestaurants = listOf(
    Restaurant(0, "Alfredo foods", "At Alfredo's we provide the best seafood dishes. We strive to serve the best fish in town!"),
    Restaurant(1, "Jaime's burgers", "At Jaime's we serve the best burgers. We also server vegan burgers!"),
    Restaurant(2, "Pizza John", "At Jaime's we serve the best burgers. We also server vegan burgers!"),
    Restaurant(3, "Dinner in the clouds", "At DIC, you can experience the full immersive dining experience." ),
    Restaurant(4, "Eternity lunch", "At Eternity lunch, you will get the best american dishes."),
    Restaurant(5, "Food and coffee", "Drink your coffee and then have lunch at FAC!" ),
    Restaurant(6, "Pizza and burgers Brazil", "Get your best burgers and pizza here in Brasil!"),
    Restaurant(7, "Merry dinner", "Get the Christmas experience at Merry dinner with Santa!"),
    Restaurant(8, "Uncle Ben's pizza shop", "Relieve the childhood pizza experience at Uncle Ben's pizza shop, now!"),
    Restaurant(9, "Spicy Grill Torontos", "Enhance your spicy diet with SG Torontos! Get the best culinary experience in Toronto."),
    Restaurant(10, "Cheese is the best", "Cheesy meals with cheesy ingredients, it's all about cheese!"),
    Restaurant(11, "Mexican spicy Food in Atlanta", "Get your spicy food dose here in Atlanta at Mexican spicy Food!"),
    Restaurant(12, "Spanish Kitchen reinvented", "Check out the true culinary experience with spanish dishes in Melenda!"),
    Restaurant(13, "Mike and Ben's food pub", ""),
)