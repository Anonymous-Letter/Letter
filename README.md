Letter App Design Project - README
===

# Letter

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
User can write an anonymous "letter" about their concerns and let others help find a solution for them!

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Social Networking
- **Mobile:** This app will be primarily developed for mobile but would also be viable on a computer. The functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Gets the user's letter and displays it to the other users to respond and get support from.  
- **Market:** Any individual above the age of thirteen could choose to use this app.
- **Habit:** This app could be used as often or unoften as the user wants depending on how deep their social life is, and what exactly they're looking for.
- **Scope:** This app starts as means to express our emotions and so it can eventually become an app that can guide people into therapy and focusing on mental well-being.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* - [X] User can log in and log out of their account.
* - [X] User can compose a letter about their concerns under a certain category (Example: love, friendship, work, study etc.).
* - [X] User can view the letters from their home timeline.
* - [X] User can refresh home timeline by pulling down to refresh (i.e pull-to-refresh)
* - [X] User can tap an anonymous letter to display a "detailed" view of that letter (e.g., category, letter content, reply button).
* - [X] User can reply to other users' letters, offering suggestions to help them.
* - [x] User will have a profile page to view their letters and the responses.
* - [X] The current signed in user is persisted across app restarts

**Optional Nice-to-have Stories**

* - [ ] A report feature that will prevent cyber-bullying.
* - [ ] Infinite scrolling feature
* - [ ] Filter letters into their respective categories

### 2. Screen Archetypes

* Login
* Home Screen - Contains Anonymous Letters
   * Upon clicking a letter title, the user will be able to view the contents.
   * User can reply by clicking the Reply button.
* Letter and Reply Screen
   * User can respond to the letter, offering some advice.
   * After composing a reponse, user can submit the reply.
* Compose Screen
   * User can choose a category of the letter to be written (Ex: love, friendship, work, study etc.)
   * User can compose a letter, penning out their concerns and submit it anonymously.
* Profile Screen
   * User can see their own letters, and the responses associated with their letters.
* Logout

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Compose
* Profile

Optional
* Settings
* Report


**Flow Navigation** (Screen to Screen)

* Login
   * Jumps to Home Screen
* Home Screen
   * Jumps to Reply Screen when prompted
   * Text field will be modified
* Compose Screen
   * Text field will be modified 
* Profile Screen
   * Jumps to Login screen when logged out

## Video Walkthrough

Here's a walkthrough of the current implemented user stories:

<img src='https://i.imgur.com/jvTlflu.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Wireframes
<img src="https://i.imgur.com/Q5HUaKU.jpg" width=600>

## Schema 
### Models
#### Letter

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| letter author |
   | category      | String   | category of letter content |
   | title         | String   | letter title |
   | content       | String   | letter content |
   | createdAt     | DateTime | date when letter is created (default field) |
   | updatedAt     | DateTime | date when letter is last updated (default field) |
   
#### Reply
    
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | user          | Pointer to User| user who reply to the targetted letter |
   | letter        | Pointer to Letter| targetted letter |
   | content       | String   | reply content |
   | report        | Bool     | report problem |
   | createdAt     | DateTime | date when reply is created (default field) |
   | updatedAt     | DateTime | date when reply is last updated (default field) |

### Networking
#### List of network requests by screen
   - Home Screen
      - (Read/GET) Query all letters
         ```swift
         let query = PFQuery(className:"Letter")
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let letters = letters {
               print("Successfully retrieved \(letters.count) letters.")
           // TODO: Do something with posts...
            }
         }
                    
      - (Read/GET) Query all categories 
      
   - Letter and Reply Screen
      - (Read/GET) Query specific letter clicked
      - (Create/POST) Create new reply object
      
   - Compose Screen
      - (Create/POST) Create a new letter object
      
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Read/GET) Query all letters from current user
      - (Read/GET) Query all replys for each letter
      - (Update/PUT) Update report
      - (Update/PUT) Update user profile image
