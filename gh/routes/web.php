<?php

// main api methods are here
Route::get('/api/hackathons', 'Api\HackathonsController@get');
Route::get('/api/hackathons/{id}', 'Api\HackathonsController@find');
Route::get('/api/rewards/{id}', 'Api\RewardsController@find');
Route::get('/api/projects/{id}', 'Api\ProjectsController@findByHack');
Route::get('/api/project/{id}', 'Api\ProjectsController@find');
Route::get('/api/orgs/all/{id}', 'Api\OrgController@findByHackathon');
Route::get('/api/orgs/{id}', 'Api\OrgController@find');
Route::get('/api/users/all/{id}', 'Api\UsersController@findByProject');
Route::get('/api/users/{id}', 'Api\UsersController@find');
Route::get('/api/content/all/{id}', 'Api\ContentController@findByProject');
Route::get('/api/content/{id}', 'Api\ContentController@find');

Route::get('/', function(){
    return view('welcome');
});