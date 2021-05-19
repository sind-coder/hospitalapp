<template>
    <div>
        <b-card bg-variant="light" border-variant="info" class="mt-2">
            <b-form-group>
                <label class="text-info">Name</label>
                <b-form-input v-model="firstName"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Lastname</label>
                <b-form-input v-model="lastName"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Diagnosis</label>
                <b-form-input v-model="diagnosis"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Age</label>
                <b-form-input type="number" min="0" v-model="age"></b-form-input>
            </b-form-group>
            <b-button block variant="outline-info" @click="save">Save</b-button>
        </b-card>
        <b-alert v-model="showDismissibleAlert" variant="success" dismissible>
            The record is successfully added!
        </b-alert>
        <b-alert v-model="dangerAlert" variant="danger" dismissible>
            All fields are required!
        </b-alert>
    </div>
</template>

<script>
    export default {
    props:['patients'],

    data: function() {
    return {
        firstName:'',
        lastName:'',
        diagnosis:'',
        age:'',
        showDismissibleAlert: false,
        dangerAlert: false
        }
    },

   methods: {
   entry(){
    return this.firstName=='' || this.lastName=='' || this.diagnosis=='' || this.age==''
    },
   save: function() {
   var id = this.$route.params.id
   var patient = {firstName: this.firstName,
                   lastName: this.lastName,
                   diagnosis: this.diagnosis,
                    age: this.age};
   if(!(this.entry())){
   this.showDismissibleAlert=true
   this.$resource('/patients{/id}').save({id: id},patient).then(data => {console.log(data);
      this.firstName = ''
      this.lastName = ''
      this.diagnosis = ''
      this.age = ''
      this.$router.go(-1)})}
      else{
      this.dangerAlert=true
      }
   }}
  }
</script>

<style>

</style>