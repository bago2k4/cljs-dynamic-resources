# cljs-dynamic-resources
[![Build Status](https://travis-ci.org/bago2k4/cljs-dynamic-resources.svg?branch=master)](https://travis-ci.org/bago2k4/cljs-dynamic-resources)

Dynamically load JavaScript and CSS resources.

## Mission

While developing web applications often happens that your html files grow very fast with all the JS and CSS resources inclusions. This can impact your app performances. Since all these files are not always needed you can often delay or avoid the load of certain resources.
For example you can load the CSS that styles a calendar widgent only when you actually use the widget.
This is where this lib helps, add the `<script>` or the `<style>` tag to the actual rendered html at runtime when you really need it.

## Overview

*cljs-dynamic-resources* helps you load JavaScript and CSS on demand. It's very easy and light weight, it uses [Prysmatic/dommy](https://github.com/Prismatic/dommy) for dom manipulation.

Let's say you have a very big JS and CSS that are needed to show a calendar widget on you web app.
You can avoid to include these resources in your html file and request them when you really need them.
For example with your web app navigate to the form that needs the calendar style and script you simply call these 2 functions:

```clojure
(require '[cljs-dynamic-resources.core :as cdd])
(cdd/add-script!
    "/widget/calendar/cal.js")
(cdd/add-style!
    "/widget/calendar/cal.css")
```

Also you can pass in a callback for the `onload` event of the style or script node:

```clojure
(cdd/add-script!
    "/widget/calendar/cal.js"
    (fn [e] (println "cal.js loaded")))
```

and also you can customize the `type` (or the `ref` if it's a style inclusion) and the where to place it of the html tag:

```clojure
(cdd/add-script!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
    (fn [e] (println "Script loaded"))
    {:type "text/my-custom-type"
     :container-sel :div#my-container-div})
```

NB: `:container-sel` should be a dommy selector, for more info refer [here](https://github.com/Prismatic/dommy)

## Test

Run:

```clojure
lein test!
```

## License

Copyright © 2015 Iacopo Carraro

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
