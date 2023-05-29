package live.baize;

import live.baize.entity.Paper;
import live.baize.service.PaperService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Level6ServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    PaperService paperService;

    String question(String question, String A, String B, String C, String D) {
        JSONObject json = new JSONObject();
        JSONObject option = new JSONObject();
        option.put("A", A);
        option.put("B", B);
        option.put("C", C);
        option.put("D", D);

        json.put("question", question);
        json.put("option", option);
        return json.toString();
    }

    void questions() {
        // 写作
        paperService.save(new Paper().setPaperId(1).setQuestionId(0).setAnswer(" ").setQuestion(question("Directions: For this part, you are allowed 30 minutes to write an essay that begins with the sentence \"People \nare now increasingly aware of the danger of 'appearance anxiety' or being obsessed with ones looks. \" You can \nmake comments, cite examples or use your personal experiences to develop your essay. You should write at least \n150 words but no more than 200 words.", "", "", "", "")));
        // 听力
        paperService.save(new Paper().setPaperId(1).setQuestionId(1).setAnswer("B").setQuestion(question("", "In a food store.", "In a restaurant. ", "In a kitchen.", "In a supermarket.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(2).setAnswer("C").setQuestion(question("", "She eats meat occasionally.", "She enjoys cheeseburgers.", "She is a partial vegetarian.", "She is allergic to seafood.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(3).setAnswer("A").setQuestion(question("", "Changing one's eating habit.", "Dealing with one's colleagues. ", "Following the same diet for years.", "Keeping awake at morning meetings.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(4).setAnswer("B").setQuestion(question("", "They are both animal lovers. ", "They enjoy perfect health. ", "They only eat organic food.", "They are cutting back on coffee.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(5).setAnswer("A").setQuestion(question("", "The man had an attitude problem.", "The man made little contribution to the company.", "The man paid attention to trivial things.", "The man got a poor evaluation from his colleagues.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(6).setAnswer("D").setQuestion(question("", "They reject employees' reasonable arguments for work efficiency.", "They make unhelpful decisions for solving problems.", "They favor some employees' suggestions over others'.", "They use manipulative language to mask their irrational choices.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(7).setAnswer("D").setQuestion(question("", "It is a good quality in the workplace.", "It is more important now than ever.", "It is a must for rational judgment.", "It is more of a sin than a virtue.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(8).setAnswer("C").setQuestion(question("", "Making rational and productive decisions.", "Focusing on employees' career growth.", "Preserving their power and prestige.", "Smoothing relationships in the workplace.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(9).setAnswer("C").setQuestion(question("", "They bring great honor to their country. ", "They create very high commercial value. ", "They accomplish feats many of us cannot.", "They show genius which defies description.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(10).setAnswer("A").setQuestion(question("", "They try to be positive role models to children. ", "They work in spare time to teach children sports. ", "They take part in kids' extra-curricular activities.", "They serve as spokespersons for luxury goods.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(11).setAnswer("D").setQuestion(question("", "Being super sports stars without appearing arrogant.", "Keeping athletes away from drug or alcohol problems.", "Preventing certain athletes from getting in trouble with the law.", "Separating an athlete's professional life from their personal life.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(12).setAnswer("D").setQuestion(question("", "They are dreams coming true to the brides.", "They should be paid up by the attendees.", "They are joyous and exciting occasions.", "They always cost more than expected.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(13).setAnswer("A").setQuestion(question("", "It was cancelled.", "It had eight guests only.", "It cost $60,000.", "It was held in Las Vegas.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(14).setAnswer("C").setQuestion(question("", "Ask her friends for help.", "Postpone her wedding.", "Keep to her budget.", "Invite more guests.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(15).setAnswer("B").setQuestion(question("", "She called it romantic.", "She rejected it flatly.", "She said she would think about it.", "She welcomed it with open arms.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(16).setAnswer("C").setQuestion(question("", "It determines people's moods. ", "It can impact people's wellbeing. ", "It can influence people's personalities. ", "It is closely related to people's emotions.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(17).setAnswer("B").setQuestion(question("", "They make people more reproductive. ", "They tend to produce positive feelings. ", "They increase people's life expectancy. ", "They may alter people's genes gradually.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(18).setAnswer("D").setQuestion(question("", "The Americans are apparently more outgoing than the Chinese. ", "People in the same geographical area may differ in personality. ", "People share many personality traits despite their nationalities. ", "The link between temperature and personality is fairly weak. ")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(19).setAnswer("C").setQuestion(question("", "A growing number of US seniors face the risk of early mortality.", "Correlations have been found between loneliness and ill health.", "Chronic loneliness does harm to senior citizens in particular.", "The number of older Americans living alone is on the rise.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(20).setAnswer("A").setQuestion(question("", "Loneliness is probably reversible.", "Being busy helps fight loneliness.", "Loneliness rarely results from living alone.", "Medication is available for treating loneliness.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(21).setAnswer("B").setQuestion(question("", "Living with one's children.", "Meaningful social contact.", "Meeting social expectations.", "Timely medical intervention.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(22).setAnswer("A").setQuestion(question("", "She had a successful career in finance .", "She wrote stories about women travelers.", "She invested in several private companies.", "She made regular trips to Asian countries.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(23).setAnswer("D").setQuestion(question("", "Travel round the world.", "Set up a travel agency.", "Buy a ranch.", "Start a blog.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(24).setAnswer("A").setQuestion(question("", "Create something unique to enter the industry.", "Gain support from travel advertising companies.", "Try to find a full-time job in the travel business.", "Work hard to attract attention from publishers.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(25).setAnswer("B").setQuestion(question("", "Refraining from promoting similar products.", "Avoiding too much advertising early on.", "Creating an exotic corporate culture.", "Attracting sufficient investment.")));

        JSONObject json = new JSONObject();
        JSONObject option = new JSONObject();
        json.put("question", "Unthinkable as it may be, humanity, every last person, could someday be wiped from the face of the Earth. \n" +
                "We have learned to worry about asteroids ( ,J,fr £ ) and super volcanoes, but the more likely --2.L, according \n" +
                "to Nick Bostrom, a professor of philosophy at Oxford, is that we humans will destroy ourselves. \n" +
                "Professor Bostrom, who directs Oxford's Future of Humanity Institute, has argued over the course of several \n" +
                "papers that human _JJ__ risks are poorly understood and, worse still, --2.L underestimated by society. Some \n" +
                "of these existential risks are fairly well known, especially the natural ones. But others are ---12..._ or even exotic. \n" +
                "Most worrying to Bostrom is the subset of existential risks that ___JQ_ from human technology, a subset that he \n" +
                "expects to grow in number and potency over the next century. \n" +
                "Despite his concerns about the risks _3_1_ to humans by technological progress, Bostrom is no luddite ( :ft \n" +
                "il:illffe _&_xt::t\" ). In fact, he is a longtime _J1_ of trans-humanism- the effort to improve the human condition, \n" +
                "and even human nature itself, through technological means. In the long run he sees technology as a bridge, a \n" +
                "bridge we humans must cross with great care, in order to reach new and better modes of being. In his work, \n" +
                "Bostrom uses the tools of philosophy and mathematics, in ___J_L, probability theory, to try and determine how \n" +
                "we as a ---1.±_ might achieve this safe passage. What follows is my conversation with Bostrom about some of the \n" +
                "most interesting and worrying existential risks that humanity might ___lL in the decades and centuries to come, \n" +
                "and about what we can do to make sure we outlast them.");
        option.put("A", "advocate");
        option.put("B", "arise");
        option.put("C", "emphasized");
        option.put("D", "encounter");
        option.put("E", "essential");
        option.put("F", "evaporation");
        option.put("G", "extinction");
        option.put("H", "obscure");
        option.put("I", "particular");
        option.put("J", "posed");
        option.put("K", "scenario");
        option.put("L", "severely");
        option.put("M", "shrewdly");
        option.put("N", "species");
        option.put("O", "variety");
        json.put("option", option);
        paperService.save(new Paper().setPaperId(1).setQuestionId(26).setAnswer("K").setQuestion(json.toString()));
        paperService.save(new Paper().setPaperId(1).setQuestionId(27).setAnswer("G").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(28).setAnswer("L").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(29).setAnswer("H").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(30).setAnswer("B").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(31).setAnswer("J").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(32).setAnswer("A").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(33).setAnswer("I").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(34).setAnswer("N").setQuestion(""));
        paperService.save(new Paper().setPaperId(1).setQuestionId(35).setAnswer("D").setQuestion(""));

        json.put("question", "San Francisco Has Become One Huge Metaphor for Economic Inequality in America\n" +
                "                A) The fog still chill the morning air and the cable cars till climb halfway to the tars. Yet on the ground, the\n" +
                "        Bay area has changed greatly since singer Tony Bennet left his heart here. Silicon Valley and the tech industry\n" +
                "        have led the region into a period of unprecedented wealth and innovation. But existing political and land\n" +
                "        limits have cau ed an alanning housing crisi and astronomical ri e in ocial and economic difference.\n" +
                "                B) While the re ident of mo t citie di play pride and support for their home industrie s, drastic market\n" +
                "        distortions in the San Francisco Bay Area ha ve created boiling resen tment in the region towards the tech\n" +
                "        indu try. vocal minority is even calling on official to puni h tho e who are benefitting from the economic\n" +
                "        and housi ng boom. If this boom and its con equence are not resolved, a drastic increase in social and\n" +
                "        economic difference may have a profound impact on the region for generation . A history and analysi of this\n" +
                "        transformation may hold invaluable in ight about the opportunitie . Peri l of tech citie are currently being\n" +
                "        cultivated acros the US, and indeed around the world.\n" +
                "                C) According to a recent tudy, San Franci co rank fir t in alifornia for economic difference. The a erage\n" +
                "        income of the top J % of hou ehold in the city averages 3.6 million . Thi i 44 time the average income\n" +
                "        of tho e at the bottom, which tand at 81 094. The top l % of the San Franci co penin ula 's hare of total\n" +
                "        income now extends to 30.8% of the region's income. This was a dramatic jump from 1989, where it stood at\n" +
                "        15.8%.\n" +
                "                D) The region' economy ha been fundamentally transformed by the technology indu try pringing from ilicon\n" +
                "        Valley. Policies pushed by Mayor Ed Lee provided tax breaks for tech companies to set up shop along the\n" +
                "        city' long-neglected Mid-Market area. The city i now home to Twitter, Uber, Airbnb, Pintere t, Dropbox\n" +
                "        and other . In hort, the Bay Area ha become a global magnet for tho e with pecialized kill , which ha\n" +
                "        in turn helped fuel economic enthu iasm, and thi economic growth has reduced unemployment to 3.4%, an\n" +
                "        admirable feat.\n" +
                "                E) In spite of all that, the strength of the recent job growth, combined with policies that have traditionally limited\n" +
                "        hou ing development in the city and throughout the penin ula, did not help ea e the affordability cri i . In\n" +
                "        2015 alone, the Bay Area added 64,000 in job . In the ame year, only 5,000 new home were built.\n" +
                "                F) With the average house in the city co ting over $ 1.25 million and average flat price over 1.11 million, the\n" +
                "        minimum qua lifying income to purchase a hou e ha increased to 254 000. Con idering that the average\n" +
                "        household income in the city currently stands at around $80,000, it is not an exaggeration to say that the\n" +
                "        dream of home ownership is now beyond the grasp of the vast majority of today's people who rent.\n" +
                "                G) For generations, the stability and prosperity of the American middle class has been anchored by home ownership. Studies have consistently shown that the value of land has overtaken overall income growth, thus\n" +
                "        providing a huge advantage to property owners as a vehicle of wealth building. When home prices soar above\n" +
                "        the reach of most households, the gap between the rich and the poor dramatically increases.\n" +
                "                H) If contributing factors leading to housing becoming less than affordable are not resolved over multiple\n" +
                "        generations, a small elite will control a vast share of the country's total wealth. The result? A society where\n" +
                "        the threat of class warfare would loom large. A society's level of happiness is tied less to measures of\n" +
                "        quantitative wealth and more to measures of qualitative wealth. This means that how a person judges their\n" +
                "        security in comparison to their neighbors' has more of an impact on their happiness than their objective\n" +
                "        standard of living. At the same time, when a system no longer provides opportunities for the majority to\n" +
                "        participate in wealth building, it not only robs those who are excluded from opportunities, but also deprives\n" +
                "        them of their dignity.\n" +
                "        I) San Francisco and the Bay Area have long been committed to values which embrace inclusion and rejection\n" +
                "        of mainstream culture. To see these values coming apart so publicly adds insult to injury for a region\n" +
                "        once defined by its progressive social fabric. In the face of resentment, it is human to want revenge. But\n" +
                "        deteriorating policies such as heavily taxing technology companies or real estate developers are not likely to\n" +
                "        shift the balance.\n" +
                "                J) The housing crisis is caused by two primary factors: the growing desirability of the Bay Area as a place\n" +
                "        to live due to its excellent economy, and our limited housing stock. Although the city is experiencing an\n" +
                "        unprecedented boom in new housing, more units are sorely needed. Protection policies were originally\n" +
                "        designed to suppress bad development and boost historic preservation in our urban areas. Now, too many\n" +
                "        developers are experiencing excessive delays. Meanwhile, there are the land limitations of the Bay Area to\n" +
                "        consider. The region is surrounded by water and mountains. Local governments need to aid development as\n" +
                "        well. This means increasing housing density throughout the region and building upwards while streamlining\n" +
                "        the approval process.\n" +
                "                K) Real estate alone will not solve the problem, of course. Transportation, too, needs to be updated and\n" +
                "        infrastructure extended to link distant regions to Silicon Valley and the city. We need to build an effective\n" +
                "        high-speed commuting system linking the high-priced and crowded Bay Area with the low-priced and low\uFFFEdensity Central Valley. This would dramatically reduce travel times. And based on the operating speeds of\n" +
                "        hovering trains used in countries such as Japan or Spain, high-speed rail could shorten the time to travel\n" +
                "        between San Francisco and California's capital, Sacramento, or from Stockton to San Jose, to under 30\n" +
                "        minutes. This system would bring once distant regions within reasonable commute to heavy job centers.\n" +
                "        The city also needs to update existing transportation routes combined with smart home-building policies\n" +
                "        that dramatically increase housing density in areas surrounding high-speed rail stations. By doing so, we\n" +
                "        will be able to build affordable housing within acceptable commuting distances for a significant bulk of the\n" +
                "        workforce.\n" +
                "                L) Our threatening housing crisis forces the difficult question of what type of society we would like to be. Will\n" +
                "        it be one where the elite command the vast bulk of wealth and regional culture is defined by an aggressive\n" +
                "        business world? We were recently treated to a taste of the latter, when local tech employee Justin Keller wrote\n" +
                "        an open letter to the city complaining about having to see homeless people on his way to work.\n" +
                "\n" +
                "                M) It doesn't have to be this way. But solutions need to be implemented now, before angry crowds grow from a\n" +
                "        nuisance to serious concern. It may take less than you might think. And in fact, the solutions to our housing\n" +
                "        crisis are already fairly clear. We need to increase the density of housing units. We need to use existing\n" +
                "        technology to shorten travel times and break the land limits. There is a way to solve complex social and\n" +
                "        economic problems without abandoning social responsibility. This is the Bay Area's opportunity to prove that\n" +
                "        it can innovate more than just technology." +
                "\n" +
                "36. San Francisco city government offered tax benefits to attract tech companies to establish operations in a less developed area."
        );
        json.remove("option");

        paperService.save(new Paper().setPaperId(1).setQuestionId(36).setAnswer("D").setQuestion(json.toString()));
        json.put("question", "37. The fast rise in the prices of land and houses increases the economic inequality among people.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(37).setAnswer("G").setQuestion(json.toString()));
        json.put("question", "38. San Francisco has been found to have the biggest income gap in California between the rich and the poor.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(38).setAnswer("C").setQuestion(json.toString()));
        json.put("question", "39. The higher rate of employment, combined with limited housing supply, did not make it any easier to buy a bouse.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(39).setAnswer("E").setQuestion(json.toString()));
        json.put("question", "40. When people compare their own living standard with others', it has a greater impact on their sense of contentment.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(40).setAnswer("H").setQuestion(json.toString()));
        json.put("question", "41. Improved transport networks connecting the city to distant outlying areas will also help solve the housing CflSIS.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(41).setAnswer("K").setQuestion(json.toString()));
        json.put("question", "42. Average incomes in the Bay Area make it virtually impossible for most tenant families to buy a home.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(42).setAnswer("F").setQuestion(json.toString()));
        json.put("question", "43. Innovative solutions to social and economic problems should be introduced before it is too late.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(43).setAnswer("M").setQuestion(json.toString()));
        json.put("question", "44. Residents of the San Francisco Bay Area strongly resent the tech industry because of the economic inequality it has contributed to.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(44).setAnswer("B").setQuestion(json.toString()));
        json.put("question", "45. One way to deal with the housing crisis is for the government to simplify approval procedures for housing projects.");
        paperService.save(new Paper().setPaperId(1).setQuestionId(45).setAnswer("J").setQuestion(json.toString()));


        paperService.save(new Paper().setPaperId(1).setQuestionId(46).setAnswer("A").setQuestion(question("The suggestion that people should aim for dietary diversity by trying to eat a variety of foods has been a \n" +
                        "basic public health recommendation for decades in the United States and elsewhere. Now, however, experts are \n" +
                        "warning that aiming for a diverse diet may actually lead to just eating more calories, and, thus, to obesity. One \n" +
                        "issue is that people may not interpret \"variety\" the way nutritionists intend. This problem is highlighted by new \n" +
                        "research conducted by the American Heart Association. Researchers reviewed all the evidence published related \n" +
                        "to dietary diversity and saw a correlation between dietary diversity and a greater intake of both healthy and \n" +
                        "unhealthy foods. This had implications for obesity, as researchers found a greater prevalence of obesity amongst \n" +
                        "people with a greater dietary diversity. \n" +
                        "One author of the new study explained that their findings contradict standard dietary advice, as most dietary \n" +
                        "guidelines around the world include a statement of eating a variety of foods. But this advice does not seem to be \n" +
                        "supported by science, possibly because there is little agreement about the meaning of \"dietary diversity,\" which" +
                        "is not clearly and consistently defined. Some experts measure dietary diversity by counting the number of food \n" +
                        "groups eaten, while others look at the distribution of calories across individual foods, and still others measure \n" +
                        "how different the foods eaten are from each other. \n" +
                        "Although the findings of this new study contradict standard dietary advice, they do not come as a surprise \n" +
                        "to all of the researchers involved. Dr. Rao, one of the study authors, noted that, after 20 years of experience in \n" +
                        "the field of obesity, he has observed that people who have a regimented lifestyle and diet tend to be thinner and \n" +
                        "healthier than people with a wide variety of consumption. This anecdotal evidence matches the conclusions of the \n" +
                        "study, which found no evidence that dietary diversity promotes healthy body weight or optimal eating patterns, \n" +
                        "and limited evidence shows that eating a variety of foods is actually associated with consuming more calories, \n" +
                        "poor eating patterns and weight gain. Further, there is some evidence that a greater variety of food options in a \n" +
                        "single meal may delay people's feeling offullness and actually increase how much they eat. \n" +
                        "Based on their findings, the researchers endorse a diet consisting of a limited number of healthy foods such \n" +
                        "as vegetables, fruits, grains, and poultry. They also recommend that people simultaneously endeavor to restrict \n" +
                        "consumption of sweets, sugar and red meat. The researchers stress, however, that their dietary recommendations \n" +
                        "do not imply dietary diversity is never positive, and that, in the past, diversity in diets of whole, unprocessed \n" +
                        "food may have actually been very beneficial.\n" +
                        "46. What has been a standard piece of dietary advice for decades?",
                "People should diversify what they eat.",
                "People should have a well-balanced diet.",
                "People should cultivate a healthy eating habit.",
                "People should limit calorie intake to avoid obesity.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(47).setAnswer("C").setQuestion(question("What did the new research by the American Heart Association find?", "Unhealthy food makes people gain weight more easily.", "Dietary diversity is positively related to good health.", "People seeking dietary diversity tend to eat more.", "Big eaters are more likely to become overweight.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(48).setAnswer("A").setQuestion(question("What could help to explain the contradiction between the new findings and the common public health recommendation?", "There is little consensus on the definition of dietary diversity.", "The methods researchers use to measure nutrition vary greatly.", "Conventional wisdom about diet is seldom supported by science.", "Most dietary guidelines around the world contradict one another.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(49).setAnswer("B").setQuestion(question("What did Dr. Rao find after 20 years of research on obesity?", "There is no clear definition of optimal eating patterns.", "Diversified food intake may not contribute to health.", "Eating patterns and weight gain go hand in hand.", "Dietary diversity promotes healthy body weight.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(50).setAnswer("C").setQuestion(question("What does the passage say about people who eat a great variety of food?", "They are more likely to eat foods beneficial to their health.", "They don 't have any problems getting sufficient nutrition. ", "They don't feel they have had enough until they overeat. ", "They tend to consume more sweets, sugar and red meat.")));


        paperService.save(new Paper().setPaperId(1).setQuestionId(51).setAnswer("A").setQuestion(question("The ability to make inferences from same and different, once thought to be unique to humans, is viewed as a \n" +
                        "cornerstone of abstract intelligent thought. A new study, however, has shown that what psychologists call same-different discrimination is present in creatures generally seen as unintelligent: newborn ducklings ( ,J, 'Pfii ). \n" +
                        "The study, published Thursday in Science, challenges our idea of what it means to have a birdbrain, said \n" +
                        "Edward Wasserman, an experimental psychologist at the University oflowa who wrote an independent review of \n" +
                        "the study. \n" +
                        "\"In fact, birds are extremely intelligent and our problem pretty much lies in figuring out how to get them to \n" +
                        "'talk' to us, or tell us how smart they really are,\" he said. \n" +
                        "Antone Martinho and Alex Kacelnik, co-authors of the new paper, devised a clever experiment to better test \n" +
                        "bird intelligence. \n" +
                        "First, they took 1-day-old ducklings and exposed them to a pair of moving objects. The two objects were \n" +
                        "either the same or different in shape or color. Then they exposed each duckling to two entirely new pairs of \n" +
                        "moving objects. \n" +
                        "The researchers found that about 70% of the ducklings preferred to move toward the pair of objects that \n" +
                        "had the same shape or color relationship as the first objects they saw. A duckling that was first shown two green \n" +
                        "spheres, in other words, was more likely to move toward a pair of blue spheres than a mismatched pair of orange \n" +
                        "and purple spheres. \n" +
                        "Ducklings go through a rapid learning process called imprinting shortly after birth- it's what allows them to \n" +
                        "identify and follow their mothers. \n" +
                        "These findings suggest that ducklings use abstract relationships between sensory inputs like color, shape, \n" +
                        "sounds and odor to recognize their mothers, said Dr. Kacelnik. \n" +
                        "By studying imprinting, the authors of this study have shown for the first time that an animal can learn \n" +
                        "relationships between concepts without training, said Jeffrey Katz, an experimental psychologist at Auburn \n" +
                        "University who was not involved in the study. \n" +
                        "Previous studies have suggested that other animals, including pigeons, dolphins, honeybees and some \n" +
                        "primates ( 3( * ~-q; 1h ) , can discern same from different, but only after extensive training. \n" +
                        "Adding ducklings to the list- particularly untrained newborn ducklings- suggests that the ability to \n" +
                        "compare abstract concepts \"is far more necessary to a wider variety of animals' survival than we previously \n" +
                        "thought,\" Dr. Martinho said. He believes the ability is so crucial because it helps animals consider context when \n" +
                        "identifying objects in their environment. \n" +
                        "It's clear from this study and others like it that \"animals process and appreciate far more of the intricacies in \n" +
                        "their world than we've ever understood,\" Dr. Wasserman said. \"We are in a revolutionary phase in terms of our \n" +
                        "ability to understand the minds of other animals.\"\n" +
                        "51 . In what way were humans thought to be unique?",
                "Being capable of same-different discrimination.",
                "Being able to distinguish abstract from concrete.",
                "Being a major source of animal intelligence.",
                "Being the cornerstone of the creative world.")));

        paperService.save(new Paper().setPaperId(1).setQuestionId(52).setAnswer("D").setQuestion(question("What do we learn from the study published in Science?", "Our understanding of the bird world was biased.", "Our communication with birds was far from adequate.", "Our knowledge about bird psychology needs updating.", "Our conception of birds' intelligence was wrong.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(53).setAnswer("B").setQuestion(question("What did the researchers discover about most ducklings from their experiment?", "They could associate shape with color.", "They could tell whether the objects were the same.", "They preferred colored objects to colorless ones.", "They reacted quickly to moving objects.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(54).setAnswer("C").setQuestion(question("What was novel about the experiment in the study reported in Science?", "The ducklings were compared with other animals.", "It was conducted by experimental psychologists.", "The animals used received no training.", "It used a number of colors and shapes.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(55).setAnswer("D").setQuestion(question("What do we learn from Dr. Wasserman's comment on the study of animal minds at the end of the passage?", "Research methods are being updated. ", "It is attracting more public attention.", "It is getting more and more intricate.", "Remarkable progress is being made.")));
        paperService.save(new Paper().setPaperId(1).setQuestionId(56).setAnswer(" ").setQuestion(question("徐霞客是中国明代的著名地理学家。他花费了三十多年的时间游遍了大半个中国。他主要靠徒步\n" +
                        "跋涉，寻访了许多荒远偏僻的地区。他把自己的见闻和考察结果详细记录下来，为后人留下了珍贵的\n" +
                        "考察资料。他通过对许多河流的实地调查，纠正了文献中关于水源的错误。他还详细地描述了地形、\n" +
                        "气候等因素对植物的影响，生动地描绘了各地的名胜古迹和风土人情。他的考察记录由后人整理成了\n" +
                        "《徐霞客游记》，在国内外产生了广泛的影响。"
                , "", "", "", "")));
    }
}
