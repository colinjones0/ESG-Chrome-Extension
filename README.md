# cs0320 Term Project 2021

**Team Members:**

**Team Strengths and Weaknesses:**
Claire:
Strengths: planning & code design?
Weaknesses: SQL, Javascript

Colin:
Strengths: Code structure, front end development
Weakness: Javascript, generics,

Jaden:
Strengths: Thinking about extensibility, program design, Javascript, looking things up and using them
Weaknesses: User Design

Ria:
Strengths: Pseudocode, Code Structure
Weaknesses: Javascript, SQL


**Project Idea(s):** _Fill this in with three unique ideas! (Due by March 1)_
Idea 1: Sustainability Impact Tracker (where i am spending my money?)

General idea/Problem: In an effort to be a conscious consumer, it can be difficult to find extensive information about a certain company’s environmental and social impact when looking for new clothes or products to buy. A lot of companies use the buzzword “sustainability” as a selling tactic without actually committing to sustainable practices. How can we as consumers become more aware of the impact our dollar is having on the environment? Are there better options?

How this solves the problem: We want to design an extension that provides data about the company's impact (using an ESG score), as well as news articles, reports, and other data, that allows the consumer to make a conscious choice about spending their money. Extra: we can also provide alternatives to other companies with similar products that have better ESG scores.

Algorithmic Complexity:

Recommendations: We can compose a Graph where nodes are companies and the edges connect companies that have similar products. Then given a product within a company, we compare the edges that are weighted by how similar the products are and we return the companies that have a better ESG score and similar products. Dealing with edge cases: ties, companies that have a very similar product but bad ESG, companies that have good ESG but not similar products.

Data Visualization: Either making our own algorithm to compute ESG scores, or making an algorithm that uses existing data to find ESG scores and assign them to a company to use for the recommendations algorithm above.

News Articles/Reports: algorithm that given a company it finds articles that can explain its ESG score.

Features:

Data Visualization - We would need to find a dataset that standardizes ESG scores and represents this information in an accessible way in the extension. This score should be comparable against other scores in that company's area that also breaks down where the company loses points - is that in environmental, social, or governance scores?

News Articles, Reports, and other data - The extension should provide some results of top articles that explore why a company’s score is the way it is. The user should be able to press on them to link to more information, or expand the extension to take them to all results about a company’s data.

Recommendations -  If a company’s ESG data is negative or their pricing is very high, we would like to provide alternative recommendations of companies that sell similar products that might have a higher ESG score, and slightly different pricing. This might use some sort of scraping and comparing algorithm using a graph with each company as a node, allowing us to compare prices of each as well as ESG scores.

Challenging Parts: Algorithm to compare products. How do we get the data about the item? How do we store all of this data? How do we compare it to data of another product and figure out how similar/different they are? Maybe this will be easier if we pick a specific industry to work with (skincare, apparel, technology, etc.)

Potential User Input:
Q: What products does this work with?
A: We may start this project starting in a single industry and then expanding from there.

**HTA Approval (crusch):** Approved — sounds very interesting, just make sure your algorithm is feasible (i.e. how will you determine edge weights?)

Idea 2: Squadify (social media based music website)
General idea/Problem: We want to create a social media type platform on which you can follow other users. This platform will be able to load your Spotify or Apple Music listening/songs data and will use it to enhance your experience by such features as: being able to create a group of friends and generating a playlist of common types of songs, suggest friends who listen to similar music, 	group listening sessions, and a personalized profile that displays your favorite songs and songs you are listening to recently.

Algorithmic Complexity: We would need to write some sort of algorithm in order to create group playlists. As such, we would need to use a database (like the 1,000,000) songs database, and find some way to establish which songs are similar to each other. The algorithm would need to find some way to do this, potentially based off of genre, artists, and other factors.

Features: Accounts that you can log into. Personal profile with your favorite songs and songs you are recently listening to (these are updated by the user). Group playlist generation. Friend suggestions. Group listening sessions.

Challenging Parts: Figuring out how to access all data: do we use a general database? Can we just pull the info from Spotify? Creating an algorithm to find similar tastes in music. Creating a sleek and usable interface.

Potential User Input:
Q: What streaming services can I connect?
A: At the moment we are looking at Spotify and potentially Apple Music but we are not quite sure yet.
Q: Will I get my own username?
A: We are planning on using the music platform username you already have, but might implement our own username feature.
Q: Can I choose my favorite/recent songs or are they auto-generated?
A: You can choose your favorite songs but recent songs will be auto-generated based on what you’re listening to.

**HTA Approval (crusch):** Rejected — cool idea, but lacks the amount of algorithmic complexity + specification we're looking for.

**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_

## How to Build and Run
_A necessary part of any README!_
