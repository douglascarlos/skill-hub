'use strict';

var gulp = require('gulp');

var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');
var rename = require('gulp-rename');

var concat = require('gulp-concat');
var uglify = require('gulp-uglify');


gulp.task('sass', function () {
    return gulp.src([
        './assets/sass/app.scss'
    ])
	    .pipe(sass.sync().on('error', sass.logError))
	    .pipe(cleanCSS())
	    .pipe(rename('app.min.css'))
	    .pipe(gulp.dest('./src/main/webapp/css'));
});

gulp.task('js', function() {
  	return gulp.src([
        './node_modules/jquery/dist/jquery.min.js',
        './node_modules/materialize-css/dist/js/materialize.min.js',
        './node_modules/bootstrap-treeview/dist/bootstrap-treeview.min.js',
        './node_modules/chart.js/dist/Chart.bundle.min.js',
        './assets/js/**/*.js'
    ])
    	.pipe(concat('app.js'))
    	.pipe(uglify())
    	.pipe(rename('app.min.js'))
    	.pipe(gulp.dest('./src/main/webapp/js'));
});

gulp.task('fonts', function () {
    return gulp.src([
        './node_modules/material-design-icons/iconfont/MaterialIcons-Regular.*'
    ])
        .pipe(gulp.dest('./src/main/webapp/fonts'));
});

gulp.task('img', function () {
    return gulp.src([
        './assets/img/**/*.png',
        './assets/img/**/*.gif'
    ])
        .pipe(gulp.dest('./src/main/webapp/images'));
});

gulp.task('default', ['sass', 'js', 'fonts', 'img']);