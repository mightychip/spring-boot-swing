# spring-boot-swing
A Java app with Swing?!  Have we travelled in time back to the 90's??  We sure have for this blog post.  Join me for a walk down memory lane with some modern twists.


## Purpose
Sometimes you have functionality that you need to make a really simple form or series of forms to access/utilize.  Sometimes you have a restrictive environment in which you need some tools running with some kind of minimal UI.  Anyways, I found myself in a situation that needed this, and I thought I'd share.

This also pointed me down the road to a plugin that was effectively broken by the introduction of lambdas.  Some day, I might take that on!!  Because of that broken plugin, there's a step in the middle where we have to use the IDE to generate some code.  This is done with IntelliJ IDEA, because it's an amazing IDE.  I wish I could say I'm a "paid shill," but I'm not.  I'm just a fan-boy.

This creates a Spring Boot application without an embedded web server, running a simple H2 database.  It saves some data, reads some data, and serves as a good launching point for this sort of thing.  I should underline the fact that Swing UI just sucks in general... but sometimes you have a limited toolset!


## How to Build it
For the most part, you just build with the typical maven `install` goal, but if you make any changes to the UI in the GUI Designer, you're going to have to build through Intellij (`Command + F9` on a Mac, `CTRL + F9` on Windows and probably on Linux, too).

## How to Run it
This is a Spring Boot application, so your typical `spring-boot:run` maven goal will do the trick.  Since it's using an in-memory database, there's no additional setup required for that.
