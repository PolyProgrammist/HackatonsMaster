<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateOrgList extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
//        public $fillable = ['id', 'hack_id', 'org_id'];
        Schema::create('org_list', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('org_id');
            $table->integer('hack_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('org_list');
    }
}
