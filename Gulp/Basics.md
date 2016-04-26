# Gulp

#### Installation
- use npm to install globally
```
sudo npm install gulp -g
```
- (optional) if you dont have an npm project
```
npm init
```
- use npm to add to your project dependencies
```
npm install gulp --save-dev
```

#### Creating a Task
```
gulp.task('task-name', function() {
  // Stuff here
});
```

#### SASS Preprocessor
- install gulp-sass 
```
npm install gulp-sass --save-dev
```
- modify gulpfile.js
```
var sass = require('gulp-sass');
.
.
gulp.task('sass', function(){
  return gulp.src('app/scss/styles.scss')
    .pipe(sass()) // Converts Sass to CSS with gulp-sass
    .pipe(gulp.dest('app/css'))
});
```


Resources:
- [Gulp for Beginners](https://css-tricks.com/gulp-for-beginners/)
