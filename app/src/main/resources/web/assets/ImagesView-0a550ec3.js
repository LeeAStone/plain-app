import{d as Ie,ah as Te,p as Ve,r as b,u as Se,s as Re,a as Ee,A as ze,n as Ae,B as Fe,C as Me,ai as Ue,D as P,af as Y,aj as Z,E as De,t as qe,ak as Be,al as Ne,H as ee,I as xe,J as Le,o as a,c as u,b as e,e as d,g as o,F as f,w as r,k as _,R as A,f as l,v as Ke,K as F,L as Qe,M as Ge,y as M,l as te,N as oe,O as He,x as Oe,z as se,S as ne,aa as We,am as Xe,a2 as je,a3 as Je,_ as Pe}from"./index-5f2856f5.js";import{u as Ye,_ as Ze,a as et}from"./list-c221660b.js";import{_ as tt}from"./FieldId-a909509e.js";import{_ as ot}from"./Multiselect-7a4215db.js";import{_ as st,a as nt}from"./grid-view-outline-rounded-c870fb0a.js";import{_ as at,a as lt}from"./label-outline-rounded-4577b874.js";import{a as it,_ as ut}from"./files-2a7c8fe0.js";import{_ as ct}from"./delete-outline-rounded-f07255a4.js";import{_ as dt}from"./Breadcrumb-cc2ecefa.js";import{n as ae}from"./list-6498ebd9.js";import{b as rt,a as _t}from"./search-82c31951.js";import{u as pt,a as mt,b as gt}from"./tags-a0407254.js";import{u as bt,_ as ht}from"./ConfirmModal.vuevuetypescriptsetuptruelang-3c66542b.js";import"./VModal.vuevuetypescriptsetuptruelang-1b277c9f.js";import"./baseIndexOf-70b929c6.js";import"./toInteger-c333620b.js";import"./vee-validate.esm-b3cf4904.js";const le=y=>(je("data-v-fb33aefd"),y=y(),Je(),y),vt={class:"v-toolbar"},ft={class:"right-actions"},yt=["title"],kt=["title"],wt=["title"],$t=["title"],Ct=["onClick"],It=["onClick"],Tt={class:"row mb-3"},Vt={class:"col-md-3 col-form-label"},St={class:"col-md-9"},Rt=["onKeyup"],Et={class:"row mb-3"},zt={class:"col-md-3 col-form-label"},At={class:"col-md-9"},Ft={class:"actions"},Mt=["onClick"],Ut={key:0,class:"row row-cols-6 g-1",style:{"margin-bottom":"24px"}},Dt=["onClick"],qt=["src"],Bt={class:"duration"},Nt={key:1,class:"table"},xt=le(()=>e("th",null,"ID",-1)),Lt=le(()=>e("th",null,null,-1)),Kt=["onClick"],Qt=["onUpdate:modelValue"],Gt=["src","onClick"],Ht={class:"badge"},Ot={key:0},Wt={colspan:"6"},Xt={class:"no-data-placeholder"},jt={key:2,class:"no-data-placeholder"},Jt=Ie({__name:"ImagesView",setup(y){var X,j,J;const ie=Te(),U=Ve(),c=b([]),{t:D}=Se(),{app:I}=Re(Ee()),p=ze({text:"",tags:[]}),h="IMAGE",T=Ae().query,v=b(parseInt(((X=T.page)==null?void 0:X.toString())??"1")),k=54,w=b(0),m=b(Fe(((j=T.q)==null?void 0:j.toString())??"")),q=b(""),{tags:V}=pt(h,m,p,async s=>{q.value=rt(s),await Me(),_e()}),i=b(((J=T.view)==null?void 0:J.toString())??"grid"),{visible:ue,index:ce,view:B,hide:de}=Ue(),{addToTags:N}=mt(h,c,V),{removeFromTags:x}=gt(h,c,V),{deleteItems:L}=bt(h,c),{downloadItems:K}=it(c,"images.zip"),S=P(()=>c.value.map(s=>({src:Y(s.fileId),name:Z(s.path),duration:0,size:s.size}))),re=P(()=>c.value.some(s=>s.checked)),{selectAll:R,toggleSelect:Q}=Ye(c),{loading:G,load:_e,refetch:pe}=De({handle:async(s,n)=>{if(n)qe(D(n),"error");else if(s){const{fileIdToken:E}=I.value,$=[];for(const C of s.images)$.push({...C,checked:!1,fileId:await Be(E,C.path)});c.value=$,w.value=s.imageCount}},document:Ne,variables:()=>({offset:(v.value-1)*k,limit:k,query:q.value}),appApi:!0});function H(){se(U,`/images?page=${v.value}&q=${ne(m.value)}&view=${i.value}`)}ee(v,()=>{H()}),ee(i,()=>{H()});function O(){m.value=_t(p),W()}function W(){se(U,`/images?q=${ne(m.value)}&view=${i.value}`)}function me(){i.value==="grid"?i.value="list":i.value="grid"}function ge(){ie.push("/files"),We(ht,{message:D("upload_images")})}return xe(()=>{Le.on("refetch_by_tag_type",s=>{s===h&&pe()})}),(s,n)=>{const E=dt,$=ct,C=ut,be=at,he=lt,ve=st,fe=nt,ye=ot,ke=Ze,we=tt,$e=et,Ce=Xe;return a(),u(f,null,[e("div",vt,[d(E,{current:()=>`${s.$t("page_title.images")} (${w.value})`},null,8,["current"]),e("div",ft,[o(re)&&i.value==="list"?(a(),u(f,{key:0},[e("button",{type:"button",class:"btn btn-action",onClick:n[0]||(n[0]=r((...t)=>o(L)&&o(L)(...t),["stop"])),title:s.$t("delete")},[d($,{class:"bi"})],8,yt),e("button",{type:"button",class:"btn btn-action",onClick:n[1]||(n[1]=r((...t)=>o(K)&&o(K)(...t),["stop"])),title:s.$t("download")},[d(C,{class:"bi"})],8,kt),e("button",{type:"button",class:"btn btn-action",onClick:n[2]||(n[2]=r((...t)=>o(N)&&o(N)(...t),["stop"])),title:s.$t("add_to_tags")},[d(be,{class:"bi"})],8,wt),e("button",{type:"button",class:"btn btn-action",onClick:n[3]||(n[3]=r((...t)=>o(x)&&o(x)(...t),["stop"])),title:s.$t("remove_from_tags")},[d(he,{class:"bi"})],8,$t)],64)):_("",!0),e("button",{type:"button",class:"btn btn-action",onClick:r(me,["stop"])},[i.value==="list"?(a(),A(ve,{key:0,class:"bi"})):_("",!0),i.value==="grid"?(a(),A(fe,{key:1,class:"bi"})):_("",!0)],8,Ct),e("button",{type:"button",class:"btn btn-action",onClick:r(ge,["stop"])},l(s.$t("upload")),9,It),d(ke,{modelValue:m.value,"onUpdate:modelValue":n[6]||(n[6]=t=>m.value=t),search:W},{filters:Ke(()=>[e("div",Tt,[e("label",Vt,l(s.$t("keywords")),1),e("div",St,[F(e("input",{type:"text","onUpdate:modelValue":n[4]||(n[4]=t=>p.text=t),class:"form-control",onKeyup:Ge(O,["enter"])},null,40,Rt),[[Qe,p.text]])])]),e("div",Et,[e("label",zt,l(s.$t("tags")),1),e("div",At,[d(ye,{modelValue:p.tags,"onUpdate:modelValue":n[5]||(n[5]=t=>p.tags=t),label:"name","track-by":"id",options:o(V)},null,8,["modelValue","options"])])]),e("div",Ft,[e("button",{type:"button",class:"btn",onClick:r(O,["stop"])},l(s.$t("search")),9,Mt)])]),_:1},8,["modelValue"])])]),i.value==="grid"?(a(),u("div",Ut,[(a(!0),u(f,null,M(o(S),(t,z)=>(a(),u("div",{class:"col",onClick:g=>o(B)(z)},[e("img",{class:"image",src:t.src+"&w=200&h=200"},null,8,qt),e("span",Bt,l(o(te)(t.size)),1)],8,Dt))),256))])):_("",!0),i.value==="list"?(a(),u("table",Nt,[e("thead",null,[e("tr",null,[e("th",null,[F(e("input",{class:"form-check-input",type:"checkbox",onChange:n[7]||(n[7]=(...t)=>o(Q)&&o(Q)(...t)),"onUpdate:modelValue":n[8]||(n[8]=t=>He(R)?R.value=t:null)},null,544),[[oe,o(R)]])]),xt,Lt,e("th",null,l(s.$t("name")),1),e("th",null,l(s.$t("tags")),1),e("th",null,l(s.$t("file_size")),1)])]),e("tbody",null,[(a(!0),u(f,null,M(c.value,(t,z)=>(a(),u("tr",{key:t.id,class:Oe({checked:t.checked}),onClick:r(g=>t.checked=!t.checked,["stop"])},[e("td",null,[F(e("input",{class:"form-check-input",type:"checkbox","onUpdate:modelValue":g=>t.checked=g},null,8,Qt),[[oe,t.checked]])]),e("td",null,[d(we,{id:t.id,raw:t},null,8,["id","raw"])]),e("td",null,[e("img",{src:o(Y)(t.fileId)+"&w=200&h=200",width:"50",height:"50",onClick:r(g=>o(B)(z),["stop"])},null,8,Gt)]),e("td",null,l(o(Z)(t.path)),1),e("td",null,[(a(!0),u(f,null,M(t.tags,g=>(a(),u("span",Ht,l(g.name),1))),256))]),e("td",null,l(o(te)(t.size)),1)],10,Kt))),128))]),c.value.length?_("",!0):(a(),u("tfoot",Ot,[e("tr",null,[e("td",Wt,[e("div",Xt,l(s.$t(o(ae)(o(G),o(I).permissions,"WRITE_EXTERNAL_STORAGE"))),1)])])]))])):_("",!0),i.value==="grid"&&o(S).length===0?(a(),u("div",jt,l(s.$t(o(ae)(o(G),o(I).permissions,"WRITE_EXTERNAL_STORAGE"))),1)):_("",!0),w.value>k?(a(),A($e,{key:3,modelValue:v.value,"onUpdate:modelValue":n[9]||(n[9]=t=>v.value=t),total:w.value,limit:k},null,8,["modelValue","total"])):_("",!0),d(Ce,{visible:o(ue),index:o(ce),sources:o(S),onHide:o(de)},null,8,["visible","index","sources","onHide"])],64)}}});const bo=Pe(Jt,[["__scopeId","data-v-fb33aefd"]]);export{bo as default};