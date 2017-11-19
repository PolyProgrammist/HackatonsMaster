<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\Org;
use App\Project;
use App\Reward;
use App\User;
use App\UserList;
use Illuminate\Http\Request;

class UsersController extends Controller
{
    public function find($id){
        return User::find($id);
    }

    public function findByHack($id){
        $list = Project::where(['hack_id' => $id])->get();
        $arr = [];
        foreach($list as $p){
            $ulist = UserList::where(['project_id' => $p->id])->get();

            foreach($ulist as $u){
                array_push($arr, User::find($u->user_id));
            }
        }
        return $arr;
    }

    public function findByProject($id){
        $list = UserList::where(['project_id' => $id])->get();

        $arr = [];
        foreach($list as $u){
            array_push($arr, User::find($u->user_id));
        }
        return $arr;
    }
}
