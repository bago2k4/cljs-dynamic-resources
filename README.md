# cljs-dynamic-resources
[![Build Status](https://travis-ci.org/bago2k4/cljs-dynamic-resources.svg?branch=master)](https://travis-ci.org/bago2k4/cljs-dynamic-resources)
[![Clojars Project](http://clojars.org/org.clojure.bago/cljs-dynamic-resources/latest-version.svg)](http://clojars.org/org.clojure.bago/cljs-dynamic-resources)

Dynamically load JavaScript and CSS resources.

## Mission

While developing web applications often happens that your html files grow very fast with all the JS and CSS inclusions.
This can impact the performances and since all these files are not always needed you can often avoid or delay the load of certain resources.
For example you can load the CSS that styles a calendar widgent only when you actually use the widget.
Add the `<script>` or the `<style>` tag to the actual rendered html at runtime when you really need it.

## Overview

*cljs-dynamic-resources* helps you load JavaScript and CSS on demand. It's very easy and light weight, it uses [Prysmatic/dommy](https://github.com/Prismatic/dommy) for dom manipulation.

Let's say you have a JS and a CSS file that are needed to show a calendar widget on you web app.
You can avoid to include these resources in your html file and request them when you really need them.
For example when your web app navigate to the form that needs the calendar widget you simply call these 2 functions:

```clojure
(require '[cljs-dynamic-resources.core :as cdr])
(cdr/add-script!
    "/widget/calendar/cal.js")
(cdr/add-style!
    "/widget/calendar/cal.css")
```

Also you can pass a callback for the `onload` event:

```clojure
(cdd/add-script!
    "/widget/calendar/cal.js"
    (fn [e] (println "cal.js loaded")))
```

and also you can customize the `type` (or the `ref` if it's a style inclusion) and the parent node:

```clojure
(cdd/add-script!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
    (fn [e] (println "Script loaded"))
    {:type "text/my-custom-js" ; or :ref "text/my-custom-css"
     :parent-sel :div#my-container-div}) ; this defaults to :body for scripts and to :head for styles
```

NB: `:parent-sel` should be a dommy selector, for more info refer [here](https://github.com/Prismatic/dommy)

## Test

Run:

```clojure
lein test!
```

## License

Copyright © 2015 Iacopo Carraro

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
