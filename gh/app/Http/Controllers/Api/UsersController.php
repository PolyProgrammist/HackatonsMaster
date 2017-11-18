<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\Org;
use App\Project;
use App\Reward;
use App\User;
use Illuminate\Http\Request;

class UsersController extends Controller
{
    public function find($id){
        return User::find($id);
    }

    public function findByProject($id){
        return User::where(['project_id' => $id])->get();
    }
}
