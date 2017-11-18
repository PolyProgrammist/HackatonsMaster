<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\Project;
use App\Reward;
use App\User;
use Illuminate\Http\Request;

class ProjectsController extends Controller
{
    public function get(){
        return Project::all();
    }

    public function find($id){
        return Project::where(['hack_id' => $id])->get();
    }
}
