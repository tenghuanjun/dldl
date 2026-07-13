package com.cy.yyjia.zhe28.domain;

import android.content.Context;
import com.contrarywind.interfaces.IPickerViewData;
import com.cy.yyjia.zhe28.domain.Area;
import com.cy.yyjia.zhe28.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Area.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0004\u001b\u001c\u001d\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00140\u0014J\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0014J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Area;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "data", "Lcom/cy/yyjia/zhe28/domain/Area$AreaBean;", "kotlin.jvm.PlatformType", "getData", "()Lcom/cy/yyjia/zhe28/domain/Area$AreaBean;", "data$delegate", "Lkotlin/Lazy;", "component1", "copy", "equals", "", "other", "getAreaList", "", "", "getCityList", "getProvinceList", "hashCode", "", "toString", "AreaBean", "CityBean", "Country", "ProvinceBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class Area {
    public static final int $stable = 8;
    private final Context context;

    /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
    private final Lazy data;

    public static /* synthetic */ Area copy$default(Area area, Context context, int i, Object obj) {
        if ((i & 1) != 0) {
            context = area.context;
        }
        return area.copy(context);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    public final Area copy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Area(context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Area) && Intrinsics.areEqual(this.context, ((Area) other).context);
    }

    public int hashCode() {
        return this.context.hashCode();
    }

    public String toString() {
        return "Area(context=" + this.context + ")";
    }

    public Area(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.data = LazyKt.lazy(new Function0<AreaBean>() { // from class: com.cy.yyjia.zhe28.domain.Area$data$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Area.AreaBean invoke() {
                return (Area.AreaBean) Util.getAssetsData(this.this$0.getContext(), "province.json", Area.AreaBean.class);
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    public final AreaBean getData() {
        return (AreaBean) this.data.getValue();
    }

    public final List<String> getProvinceList() {
        ArrayList arrayList = new ArrayList();
        Iterator<ProvinceBean> it = getData().getPr().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }

    public final List<List<String>> getCityList() {
        ArrayList arrayList = new ArrayList();
        for (ProvinceBean provinceBean : getData().getPr()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<CityBean> it = provinceBean.getCity().iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().getName());
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public final List<List<List<String>>> getAreaList() {
        ArrayList arrayList = new ArrayList();
        for (ProvinceBean provinceBean : getData().getPr()) {
            ArrayList arrayList2 = new ArrayList();
            for (CityBean cityBean : provinceBean.getCity()) {
                ArrayList arrayList3 = new ArrayList();
                Iterator<Country> it = cityBean.getArea().iterator();
                while (it.hasNext()) {
                    arrayList3.add(it.next().getName());
                }
                arrayList2.add(arrayList3);
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: Area.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Area$AreaBean;", "", "pr", "", "Lcom/cy/yyjia/zhe28/domain/Area$ProvinceBean;", "(Ljava/util/List;)V", "getPr", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class AreaBean {
        public static final int $stable = 8;
        private final List<ProvinceBean> pr;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AreaBean copy$default(AreaBean areaBean, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = areaBean.pr;
            }
            return areaBean.copy(list);
        }

        public final List<ProvinceBean> component1() {
            return this.pr;
        }

        public final AreaBean copy(List<ProvinceBean> pr) {
            Intrinsics.checkNotNullParameter(pr, "pr");
            return new AreaBean(pr);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AreaBean) && Intrinsics.areEqual(this.pr, ((AreaBean) other).pr);
        }

        public int hashCode() {
            return this.pr.hashCode();
        }

        public String toString() {
            return "AreaBean(pr=" + this.pr + ")";
        }

        public AreaBean(List<ProvinceBean> pr) {
            Intrinsics.checkNotNullParameter(pr, "pr");
            this.pr = pr;
        }

        public final List<ProvinceBean> getPr() {
            return this.pr;
        }
    }

    /* JADX INFO: compiled from: Area.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Area$ProvinceBean;", "Lcom/contrarywind/interfaces/IPickerViewData;", "name", "", "city", "", "Lcom/cy/yyjia/zhe28/domain/Area$CityBean;", "(Ljava/lang/String;Ljava/util/List;)V", "getCity", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "getPickerViewText", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ProvinceBean implements IPickerViewData {
        public static final int $stable = 8;
        private final List<CityBean> city;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ProvinceBean copy$default(ProvinceBean provinceBean, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = provinceBean.name;
            }
            if ((i & 2) != 0) {
                list = provinceBean.city;
            }
            return provinceBean.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<CityBean> component2() {
            return this.city;
        }

        public final ProvinceBean copy(String name, List<CityBean> city) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(city, "city");
            return new ProvinceBean(name, city);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProvinceBean)) {
                return false;
            }
            ProvinceBean provinceBean = (ProvinceBean) other;
            return Intrinsics.areEqual(this.name, provinceBean.name) && Intrinsics.areEqual(this.city, provinceBean.city);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.city.hashCode();
        }

        public String toString() {
            return "ProvinceBean(name=" + this.name + ", city=" + this.city + ")";
        }

        public ProvinceBean(String name, List<CityBean> city) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(city, "city");
            this.name = name;
            this.city = city;
        }

        public final String getName() {
            return this.name;
        }

        public final List<CityBean> getCity() {
            return this.city;
        }

        @Override // com.contrarywind.interfaces.IPickerViewData
        public String getPickerViewText() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: Area.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Area$CityBean;", "Lcom/contrarywind/interfaces/IPickerViewData;", "name", "", "area", "", "Lcom/cy/yyjia/zhe28/domain/Area$Country;", "(Ljava/lang/String;Ljava/util/List;)V", "getArea", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "getPickerViewText", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CityBean implements IPickerViewData {
        public static final int $stable = 8;
        private final List<Country> area;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CityBean copy$default(CityBean cityBean, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cityBean.name;
            }
            if ((i & 2) != 0) {
                list = cityBean.area;
            }
            return cityBean.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<Country> component2() {
            return this.area;
        }

        public final CityBean copy(String name, List<Country> area) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(area, "area");
            return new CityBean(name, area);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CityBean)) {
                return false;
            }
            CityBean cityBean = (CityBean) other;
            return Intrinsics.areEqual(this.name, cityBean.name) && Intrinsics.areEqual(this.area, cityBean.area);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.area.hashCode();
        }

        public String toString() {
            return "CityBean(name=" + this.name + ", area=" + this.area + ")";
        }

        public CityBean(String name, List<Country> area) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(area, "area");
            this.name = name;
            this.area = area;
        }

        public final String getName() {
            return this.name;
        }

        public final List<Country> getArea() {
            return this.area;
        }

        @Override // com.contrarywind.interfaces.IPickerViewData
        public String getPickerViewText() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: Area.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u0003H\u0016J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Area$Country;", "Lcom/contrarywind/interfaces/IPickerViewData;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getPickerViewText", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Country implements IPickerViewData {
        public static final int $stable = 0;
        private final String name;

        public static /* synthetic */ Country copy$default(Country country, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = country.name;
            }
            return country.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Country copy(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Country(name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Country) && Intrinsics.areEqual(this.name, ((Country) other).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Country(name=" + this.name + ")";
        }

        public Country(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }

        @Override // com.contrarywind.interfaces.IPickerViewData
        public String getPickerViewText() {
            return this.name;
        }
    }
}
