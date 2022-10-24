library(dplyr)

base.summer <- read.csv("path_to/test2.csv")

base.winter <- read.csv("path_to/test.csv")


# base.medal.1924 <- read.csv("path_to/compte_medal_winter_1924.csv")
# base.medal.1924
# 
# head(base.dictionary)
# head(base.summer)
# head(base.winter)
# head(base.host)
# 
# #final.base.dictionary <- select(base.dictionary, Country, Code)
# #write.csv(final.base.host, "path_to/final_dictionary.csv")
# 
# 
# #final.base.host <- select(base.host, City, Country, Summer..Olympiad., Winter, Year)
# #final.base.host$row_num <- seq.int(nrow(final.base.host))
# #final.base.host <- subset(final.base.host, row_num<=54, select=c(City, Country, Summer..Olympiad., Winter, Year))
# #write.csv(final.base.host, "path_to/final_host_city.csv")
# 
# test_1924 <- filter(base.winter, Year == 1924)
# head(test_1924)
# 
# compte_medal_winter_1924 <- with(test_1924, table(Country, Medal))
# compte_medal_winter_1924 <- rowSums(compte_medal_winter_1924)
# compte_medal_winter_1924
# write.csv(compte_medal_winter_1924, "path_to/compte_medal_winter_1924.csv")
# 
# 
# compte_medal_winter <- with(base.winter, table(Country, Medal))
# compte_medal_winter <- rowSums(compte_medal_winter)
# compte_medal_winter
# #write.csv(compte_medal_winter, "path_to/compte_medal_winter.csv")
# 


base.summer
base.winter[base.winter$Country=="AUT",]
df <- data.frame(base.winter)
countries <- unique(base.winter$Country)
years <- unique(base.winter$Year)

for (variable in countries) {
  pred = ""
  print(variable)
  for(varYear in years){
    tmp = base.winter[base.winter$Country==variable,]
    if(pred != ""){
      base.winter[base.winter$Country==variable & base.winter$Year==varYear,]$Medal = sum(c(tmp$Medal,base.winter[base.winter$Country==variable & base.winter$Year==varYear,]$Medal))
    }
    pred = tmp$Year
  }
  base.winter[base.winter$Country==variable,]$Medal
  print(variable)
  base.winter[base.winter$Country==variable & base.winter$Year==varYear,]$Medal
}

base.winter[base.winter$Country==variable,]
