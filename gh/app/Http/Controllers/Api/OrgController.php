<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\Org;
use App\Project;
use App\Reward;
use App\User;
use Illuminate\Http\Request;

class OrgController extends Controller
{
    public function find($id){
        return Org::find($id);
    }

    public function findByHackathon($id){
        return OrgList::where(['hack_id' => $id])->get();
    }
}
