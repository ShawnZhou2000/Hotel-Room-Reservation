import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [{
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  //测试页面
  {
    path: '/',
    component: Layout,
    children: [{
      path: '/',
      name: '/',
      component: () => import('@/views/index'),
      meta: {
        title: '主页',
        icon: 'dashboard'
      }
    }]
  },
  // 用户管理
  {
    path: '/user',
    component: Layout,
    // redirect: '/user/upload',
    meta: {
      title: '用户管理',
      icon: 'user'
    },
    children: [{
        path: 'query-user',
        name: 'query-user',
        component: () => import('@/views/user/queryuser'),
        meta: {
          title: '查询用户',
          icon: 'queryuser',
        }
      },
      {
          path: 'change-user',
          name: 'change-user',
          component: () => import('@/views/user/changeuser'),
          meta: {
            title: '修改用户',
            icon: 'changeuser',
          }
        },
      {
        path: 'delete-user',
        name: 'delete-user',
        component: () => import('@/views/user/deleteuser'),
        meta: {
          title: '删除用户',
          icon: 'deleteuser',
        }
      },
    ]
  },

  // 订单管理
  {
    path: '/order',
    component: Layout,
    // redirect: '/user/upload',
    meta: {
      title: '订单管理',
      icon: 'form'
    },
    children: [{
        path: 'order-list',
        name: 'order-list',
        component: () => import('@/views/order/orderlist'),
        meta: {
          title: '等待列表',
          icon: 'orderlist',
        }
      },
      {
        path: 'order-reject',
        name: 'order-reject',
        component: () => import('@/views/order/orderreject'),
        meta: {
          title: '取消订单',
          icon: 'orderreject',
        }
      },
      {
        path: 'order-accept',
        name: 'order-accept',
        component: () => import('@/views/order/orderaccept'),
        meta: {
          title: '接受订单',
          icon: 'orderaccept',
        }
      },
    ]
  },
  // 房间管理
  {
    path: '/room',
    component: Layout,
    // redirect: '/user/upload',
    meta: {
      title: '房间管理',
      icon: 'room'
    },
    children: [{
        path: 'room-list',
        name: 'room-list',
        component: () => import('@/views/room/roomlist'),
        meta: {
          title: '房间列表',
          icon: 'roomlist',
        }
      },
      {
        path: 'room-search',
        name: 'room-search',
        component: () => import('@/views/room/roomsearch'),
        meta: {
          title: '详情查询',
          icon: 'roomsearch',
        },
      },
      {
        path: 'room-add',
        name: 'room-add',
        component: () => import('@/views/room/roomadd'),
        meta: {
          title: '添加房间',
          icon: 'roomadd',
        }
      },
      {
        path: 'room-change',
        name: 'room-change',
        component: () => import('@/views/room/roomchange'),
        meta: {
          title: '修改房间',
          icon: 'roomchange',
        }
      },
      {
        path: 'room-delete',
        name: 'room-delete',
        component: () => import('@/views/room/roomdelete'),
        meta: {
          title: '删除房间',
          icon: 'roomdelete',
        }
      },
    ]
  },
  {
    path: '/comment',
    component: Layout,
    children: [{
      path: '/comment',
      name: '/comment',
      component: () => import('@/views/comment/comment'),
      meta: {
        title: '评论管理',
        icon: 'comment'
      }
    }]
  },
  // 404 page must be placed at the end !!!
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
