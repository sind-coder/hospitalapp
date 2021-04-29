<template>
    <div>
        <b-card bg-variant="light" border-variant="info" class="mt-2">
            <b-form-group class="text-info">
                <label class="text-info">Name</label>
                <b-form-input v-model="firstName"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Lastname</label>
                <b-form-input v-model="lastName"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Position</label>
                <b-form-input v-model="position"></b-form-input>
            </b-form-group>
            <b-form-group>
                <label class="text-info">Start Time</label>
                <b-input-group class="mb-3">
                    <b-form-input
                            id="time_s"
                            v-model="time_start"
                            type="text"
                            placeholder="HH:mm"
                            :state="validation"
                    ></b-form-input>
                    <b-input-group-append>
                        <b-form-timepicker
                                v-model="time_start"
                                button-only
                                right
                                locale="ru"
                                :state="validation"
                                aria-controls="example-input"
                        ></b-form-timepicker>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
            <b-form-group>
                <label class="text-info">End Time</label>
                <b-input-group class="mb-3">
                    <b-form-input
                            id="time_e"
                            v-model="time_end"
                            type="text"
                            placeholder="HH:mm"
                            :state="validation"
                    ></b-form-input>
                    <b-input-group-append>
                        <b-form-timepicker
                                v-model="time_end"
                                button-only
                                right
                                locale="ru"
                                :state="validation"
                                aria-controls="example-input"
                        ></b-form-timepicker>
                    </b-input-group-append>
                </b-input-group>
                <b-form-invalid-feedback :state="validation">
                    Время начала не может быть больше времени окончания
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validation">
                </b-form-valid-feedback>
            </b-form-group>
            <b-button block variant="outline-info" @click="save">Save</b-button>
        </b-card>
        <b-alert v-model="showDismissibleAlert" variant="success" dismissible>
            The record is successfully added!
        </b-alert>
        <b-alert v-model="showDangerAlert" variant="danger" dismissible>
            Check the entered start time and end time!
        </b-alert>
        <b-alert v-model="dangerAlert" variant="danger" dismissible>
            All fields are required!
        </b-alert>
    </div>

</template>

<script>

    export default {
        props:['doctors', 'doctorAttr'],

    data() {
    return {
        firstName:'',
        lastName:'',
        position:'',
        time_start:'',
        time_end:'',
        id:'',
        showDismissibleAlert: false,
        showDangerAlert: false,
        dangerAlert: false}
    },
computed: {
      validation() {
        return this.time_start < this.time_end
      }
    },
   methods: {
   valid() {
        return this.time_start < this.time_end
    },
    entry(){
    return this.firstName=='' || this.lastName=='' || this.position==''
    },
   save() {
   var doctor = {firstName: this.firstName,
                   lastName: this.lastName,
                   position: this.position,
                   time_start: String(this.time_start),
                    time_end: String(this.time_end)};

   if(this.valid() && !(this.entry())){
   this.showDismissibleAlert=true
   console.log('create');
   this.$resource('/doctor{/id}').save({},doctor).then(result =>
   result.json().then(data =>{
   this.firstName = ''
   this.lastName = ''
   this.position = ''
   this.time_start = ''
   this.time_end = ''
   this.$router.go(-1)}))
   } else {
   if(this.entry()){
   this.dangerAlert=true}
   if (!(this.valid())){
   this.showDangerAlert=true
   }
   }
   }}
  }
</script>

<style>

</style>