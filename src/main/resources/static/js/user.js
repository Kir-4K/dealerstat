function getIndex(list, id) {
    for (let i = 0; i < list; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

let userApi = Vue.resource('/users{/id}');

Vue.component('user-form', {
    props: ['users', 'userAttr'],
    data: function () {
        return {
            firstName: '',
            lastName: '',
            password: '',
            email: '',
            id: ''
        }
    },
    watch: {
        userAttr: function (newVal) {
            this.firstName = newVal.firstName;
            this.lastName = newVal.lastName;
            this.password = newVal.password;
            this.email = newVal.email;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Введите имя" v-model="firstName" />' +
        '<input type="text" placeholder="Введите фамилию" v-model="lastName" />' +
        '<input type="text" placeholder="Введите пароль" v-model="password" />' +
        '<input type="text" placeholder="Введите почту" v-model="email" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            let user = {
                firstName: this.firstName,
                lastName: this.lastName,
                password: this.password,
                email: this.email,
            };

            if (this.id) {
                userApi.update({id: this.id}, user).then(result =>
                    result.json().then(data => {
                        let index = getIndex(this.data, data.id);
                        this.users.splice(index, 1, data);
                        this.firstName = '';
                        this.lastName = '';
                        this.password = '';
                        this.email = '';
                        this.id = '';
                    }))
            } else {
                userApi.save({}, user).then(result =>
                    result.json().then(data => {
                        this.users.push(data);
                        this.firstName = '';
                        this.lastName = '';
                        this.password = '';
                        this.email = '';
                    })
                )
            }
        }
    }
});

Vue.component('user-row', {
    props: ['user', 'editUser', 'users'],
    template:
        '<div>' +
        '<i>({{ user.id }})</i> {{ user.email }} ' +
        '<span style="position: absolute; right: 0;">' +
        '<input type="button" value="Edit" @click="edit">' +
        '<input type="button" value="Delete" @click="del">' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editUser(this.user)
        },
        del: function () {
            userApi.remove({id: this.user.id}).then(result => {
                if (result.ok) {
                    this.users.splice(this.users.indexOf(this.user), 1)
                }
            })
        }
    }
});

Vue.component('users-list', {
    props: ['users'],
    data: function () {
        return {
            user: null,
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<user-form :users="users" :userAttr="user"/>' +
        '<user-row v-for="user in users" v-bind:key="user.id" ' +
        ':user="user" :editUser="editUser" :users="users"/>' +
        '</div>',
    methods: {
        editUser: function (user) {
            this.user = user;
        }
    }
});

let app = new Vue({
    el: '#app',
    template: '<users-list :users="users"/>',
    data: {
        users: []
    },
    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    }
});
