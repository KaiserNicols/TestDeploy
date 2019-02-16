import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TempMainComponent } from './temp-main.component';

describe('TempMainComponent', () => {
  let component: TempMainComponent;
  let fixture: ComponentFixture<TempMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TempMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TempMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
